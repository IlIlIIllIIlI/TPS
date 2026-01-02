package fr.bts.iris.slam.service;

import fr.bts.iris.slam.Difficulty;
import fr.bts.iris.slam.dao.DeckCardAssociationDAO;
import fr.bts.iris.slam.dao.DeckDAO;
import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.dao.ReviewHistoryDAO;
import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.model.FlashCard;
import fr.bts.iris.slam.model.ReviewHistory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionServiceTest {
    private RevisionService service;
    private DeckDAO deckDAO;
    private FlashCardDAO flashCardDAO;
    private ReviewHistoryDAO reviewHistoryDAO;
    private Connection connection;
    private DeckCardAssociationDAO deckCardAssociationDAO;

    @BeforeEach
    void setUp() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        this.deckDAO = new DeckDAO(this.connection);
        this.flashCardDAO = new FlashCardDAO(this.connection);
        this.reviewHistoryDAO = new ReviewHistoryDAO(this.connection);
        this.deckCardAssociationDAO = new DeckCardAssociationDAO(this.connection);

        this.service = new RevisionService(this.deckDAO, this.reviewHistoryDAO, this.flashCardDAO);
    }
    @AfterEach
    void tearDown() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }

    @Test
    void shouldCreateServiceWithValidDAOs() {
        assertDoesNotThrow(() -> new RevisionService(this.deckDAO, this.reviewHistoryDAO, this.flashCardDAO));
    }

    @Test
    void shouldRejectNullDAOs() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new RevisionService(null, null, null)
        );
        assertEquals("DAO cannot be null", exception.getMessage());
    }

    @Test
    void shouldProcessRevisionEasy() {
        FlashCard card = new FlashCard("Q", "A");
        card.setIntervalDays(1);
        card.setEaseFactor(2.5);

        this.flashCardDAO.save(card);
        int cardId = card.getId();

        this.service.processRevision(card, Difficulty.EASY);

        FlashCard updatedCard = this.flashCardDAO.findById(cardId);

        assertEquals(3, updatedCard.getIntervalDays());
        assertEquals(2.65, updatedCard.getEaseFactor(), 0.001);
        assertEquals(LocalDate.now().plusDays(3), updatedCard.getNextReviewDate());
    }

    @Test
    void shouldProcessRevisionMedium() {
        FlashCard card = new FlashCard("Q", "A");
        card.setIntervalDays(10);
        card.setEaseFactor(2.5);
        this.flashCardDAO.save(card);

        this.service.processRevision(card, Difficulty.MEDIUM);

        FlashCard updatedCard = this.flashCardDAO.findById(card.getId());

        assertEquals(15, updatedCard.getIntervalDays());
        assertEquals(2.5, updatedCard.getEaseFactor(), 0.001);
        assertEquals(LocalDate.now().plusDays(15), updatedCard.getNextReviewDate());
    }

    @Test
    void shouldProcessRevisionHard() {
        FlashCard card = new FlashCard("Q", "A");
        card.setIntervalDays(10);
        card.setEaseFactor(2.5);
        this.flashCardDAO.save(card);

        this.service.processRevision(card, Difficulty.HARD);

        FlashCard updatedCard = this.flashCardDAO.findById(card.getId());

        assertEquals(1, updatedCard.getIntervalDays());
        assertEquals(2.3, updatedCard.getEaseFactor(), 0.001);
        assertEquals(LocalDate.now().plusDays(1), updatedCard.getNextReviewDate());
    }

    @Test
    void shouldCreateHistoryOnRevision() {
        FlashCard card = new FlashCard("Q", "A");
        this.flashCardDAO.save(card);

        this.service.processRevision(card, Difficulty.EASY);

        List<ReviewHistory> history = this.reviewHistoryDAO.findByCardId(card.getId());
        assertEquals(1, history.size());
        assertEquals(Difficulty.EASY, history.getFirst().getDifficulty());
    }


    @Test
    void shouldGetCardsDueForDeck() throws SQLException {
        this.deckDAO.save(new Deck(1,"A", "B"));


        FlashCard c1 = new FlashCard("q", "A");
        c1.setNextReviewDate(LocalDate.now());
        this.flashCardDAO.save(c1);

        FlashCard c2 = new FlashCard("retard", "A");
        c2.setNextReviewDate(LocalDate.now().minusDays(1));
        this.flashCardDAO.save(c2);

        FlashCard c3 = new FlashCard("avance", "A");
        c3.setNextReviewDate(LocalDate.now().plusDays(1));
        this.flashCardDAO.save(c3);


        ArrayList<FlashCard> cards = this.flashCardDAO.findAll();
        Deck deck = this.deckDAO.findAll().getFirst();
        this.deckCardAssociationDAO.save(deck.getId(),cards.getFirst().getId());
        this.deckCardAssociationDAO.save(deck.getId(),cards.get(1).getId());
        this.deckCardAssociationDAO.save(deck.getId(),cards.getLast().getId());

        ArrayList<FlashCard> dueCards = this.service.getCardsDueForDeck(deck.getId());

        assertEquals(2, dueCards.size());
    }

}
