package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.Deck;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DeckDAOTest {
    private DeckDAO deckDAO;
    private Connection testConnection;

    @BeforeEach
    void setUp() throws SQLException {
        this.testConnection = DriverManager.getConnection("jdbc:sqlite::memory:");
        this.deckDAO = new DeckDAO(this.testConnection);


    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.testConnection != null && !this.testConnection.isClosed()) {
            this.testConnection.close();
        }
    }

    @Test
    void ShouldCreateTableDecks(){
        assertDoesNotThrow(() -> new DeckDAO(this.testConnection));
    }

    @Test
    void shouldInsertDeckAndGenerateId(){
        Deck deck = new Deck("yoou", "oui");

        this.deckDAO.save(deck);

        assertNotEquals(0, deck.getId());
    }

    @Test
    void shouldFindAllDecks(){
        Deck deck1 = new Deck("Deck 1", "Des 1");
        Deck deck2 = new Deck("Deck 2", "Des 2");
        Deck deck3 = new Deck("Deck 3", "Des 3");

        this.deckDAO.save(deck1);
        this.deckDAO.save(deck2);
        this.deckDAO.save(deck3);

        assertEquals(3, this.deckDAO.findAll().size());
    }

    @Test
    void shouldUpdateExistingDeck(){
        Deck deck = new Deck("yipee", "yipeea");
        this.deckDAO.save(deck);

        Deck updatedDeck = new Deck(deck.getId(), "ame", "Nfjuz");

        this.deckDAO.update(updatedDeck);

        Deck finalDeck = this.deckDAO.findAll().getFirst();
        assertEquals("ame", finalDeck.getName());
        assertEquals("Nfjuz", finalDeck.getDescription());
    }

    @Test
    void shouldDeleteDeckByName(){
        Deck deck = new Deck("del", "aa");
        this.deckDAO.save(deck);

        this.deckDAO.deleteByName("del");

        assertTrue(this.deckDAO.findAll().isEmpty());
    }

    @Test
    void shouldFindByIdWithCards() {
        Deck deck = new Deck("deck", ":D");
        this.deckDAO.save(deck);

        Deck retrieved = this.deckDAO.findByIdWithCards(deck.getId());

        assertEquals("deck", retrieved.getName());
        assertTrue(retrieved.getCards().isEmpty());
    }
}

