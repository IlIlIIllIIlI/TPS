package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.model.FlashCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DeckCardAssociationDAOTest {
    private DeckCardAssociationDAO associationDAO;
    private FlashCardDAO flashCardDAO;
    private DeckDAO deckDAO;
    private Connection testConnection;

    private int validDeckId;
    private int validCardId;

    @BeforeEach
    void setUp() throws SQLException {
        this.testConnection = DriverManager.getConnection("jdbc:sqlite::memory:");

        try (var stmt = this.testConnection.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON;");
        }

        this.deckDAO = new DeckDAO(this.testConnection);
        this.flashCardDAO = new FlashCardDAO(this.testConnection);
        this.associationDAO = new DeckCardAssociationDAO(this.testConnection);

        Deck deck = new Deck("Deck", "Description");
        this.deckDAO.save(deck);
        this.validDeckId = deck.getId();

        FlashCard card = new FlashCard("Question", "Answer");
        this.flashCardDAO.save(card);
        this.validCardId = card.getId();
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.testConnection != null && !this.testConnection.isClosed()) {
            this.testConnection.close();
        }
    }

    @Test
    void ShouldCreateAssociationTable() {
        assertDoesNotThrow(() -> new DeckCardAssociationDAO(this.testConnection));
    }

    @Test
    void shouldSaveAssociation() {
        assertDoesNotThrow(() -> this.associationDAO.save(validDeckId, validCardId));

        ArrayList<Integer> cardIds = this.associationDAO.findAllCardsByDeck(validDeckId);
        assertTrue(cardIds.contains(validCardId));
    }

    @Test
    void shouldFindAllCardsByDeck() {
        FlashCard card2 = new FlashCard("Q2", "A2");
        this.flashCardDAO.save(card2);
        int validCardId2 = card2.getId();

        this.associationDAO.save(validDeckId, validCardId);
        this.associationDAO.save(validDeckId, validCardId2);

        ArrayList<Integer> results = this.associationDAO.findAllCardsByDeck(validDeckId);

        assertEquals(2, results.size());
        assertTrue(results.contains(validCardId));
        assertTrue(results.contains(validCardId2));
    }

    @Test
    void shouldDeleteSpecificAssociation() {
        this.associationDAO.save(validDeckId, validCardId);

        this.associationDAO.deleteByAssociation(validDeckId, validCardId);

        ArrayList<Integer> results = this.associationDAO.findAllCardsByDeck(validDeckId);
        assertTrue(results.isEmpty());
    }

    @Test
    void shouldDeleteAllAssociationsForDeck() {
        FlashCard card2 = new FlashCard("Q2", "A2");
        this.flashCardDAO.save(card2);

        this.associationDAO.save(validDeckId, validCardId);
        this.associationDAO.save(validDeckId, card2.getId());

        this.associationDAO.deleteDeckByDeckId(validDeckId);

        ArrayList<Integer> results = this.associationDAO.findAllCardsByDeck(validDeckId);
        assertTrue(results.isEmpty());
    }
}
