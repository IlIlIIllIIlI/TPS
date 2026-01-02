package fr.bts.iris.slam.service;

import fr.bts.iris.slam.dao.DeckCardAssociationDAO;
import fr.bts.iris.slam.dao.DeckDAO;
import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.model.FlashCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckServiceTest {
    private DeckService deckService;
    private DeckDAO deckDAO;
    private DeckCardAssociationDAO associationDAO;
    private FlashCardDAO flashCardDAO;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        this.deckDAO = new DeckDAO(this.connection);
        this.associationDAO = new DeckCardAssociationDAO(this.connection);
        this.flashCardDAO = new FlashCardDAO(this.connection);

        this.deckService = new DeckService(this.deckDAO, this.associationDAO);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }

    @Test
    void shouldCreateServiceWithValidDAOs() {
        assertDoesNotThrow(() -> new DeckService(this.deckDAO, this.associationDAO));
    }

    @Test
    void shouldRejectNullDeckDAO() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DeckService(null, this.associationDAO)
        );
        assertEquals("DAO cannot be null", exception.getMessage());
    }

    @Test
    void shouldRejectNullAssociationDAO() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DeckService(this.deckDAO, null)
        );
        assertEquals("DAO cannot be null", exception.getMessage());
    }


    @Test
    void shouldUpdateDeckComposition() {
        Deck deck = new Deck("Test Deck", "Desc");
        this.deckDAO.save(deck);
        int deckId = deck.getId();

        FlashCard c1 = new FlashCard("Q1", "A1");
        FlashCard c2 = new FlashCard("Q2", "A2");
        this.flashCardDAO.save(c1);
        this.flashCardDAO.save(c2);

        List<Integer> cardIdsToLink = Arrays.asList(c1.getId(), c2.getId());

        this.deckService.updateDeckComposition(deckId, cardIdsToLink);

        ArrayList<Integer> linkedCards = this.associationDAO.findAllCardsByDeck(deckId);
        assertEquals(2, linkedCards.size());
    }

    @Test
    void shouldLoadDecksWithFlashCards() {
        // ARRANGE
        Deck d1 = new Deck("Deck 1", "Des 1");
        this.deckDAO.save(d1);
        FlashCard c1 = new FlashCard("Q1", "A1");
        this.flashCardDAO.save(c1);
        this.associationDAO.save(d1.getId(), c1.getId());

        Deck d2 = new Deck("Deck 2", "Des 2");
        this.deckDAO.save(d2);

        // ACT
        ArrayList<Deck> loadedDecks = this.deckService.loadWithFlashCards();

        // ASSERT
        assertEquals(2, loadedDecks.size());
        assertEquals(1,loadedDecks.getFirst().getCards().size());
    }

    @Test
    void shouldCheckIfCardIsInDeck() {
        // ARRANGE
        Deck deck = new Deck("Deck", "Des");
        this.deckDAO.save(deck);

        FlashCard cardIn = new FlashCard("In", "A");
        FlashCard cardOut = new FlashCard("Out", "B");
        this.flashCardDAO.save(cardIn);
        this.flashCardDAO.save(cardOut);

        this.associationDAO.save(deck.getId(), cardIn.getId());

        // ACT & ASSERT
        assertTrue(this.deckService.isInDeck(deck.getId(), cardIn.getId()));
        assertFalse(this.deckService.isInDeck(deck.getId(), cardOut.getId()));
    }

    @Test
    void shouldClearDeckComposition() {
        // ARRANGE
        Deck deck = new Deck("C", "Des");
        this.deckDAO.save(deck);
        FlashCard c1 = new FlashCard("Q", "A");
        this.flashCardDAO.save(c1);
        this.associationDAO.save(deck.getId(), c1.getId());

        // ACT
        this.deckService.updateDeckComposition(deck.getId(), new ArrayList<>());

        // ASSERT
        assertTrue(this.associationDAO.findAllCardsByDeck(deck.getId()).isEmpty());
    }
}
