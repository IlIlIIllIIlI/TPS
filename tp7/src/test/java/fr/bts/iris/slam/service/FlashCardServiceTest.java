package fr.bts.iris.slam.service;

import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.model.FlashCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FlashCardServiceTest {
    private FlashCardService service;
    private FlashCardDAO dao;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        this.dao = new FlashCardDAO(this.connection);

        this.service = new FlashCardService(this.dao);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }

    @Test
    void shouldCreateServiceWithValidDAO() {
        assertDoesNotThrow(() -> new FlashCardService(this.dao));
    }

    @Test
    void shouldRejectNullDAO() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new FlashCardService(null)
        );
        assertEquals("DAO cannot be null", exception.getMessage());
    }

    @Test
    void shouldSaveFlashCardThroughService() {
        FlashCard card = new FlashCard("Question", "Answer");

        this.service.saveFlashCard(card);

        assertNotEquals(0, card.getId());

        ArrayList<FlashCard> allCards = this.service.getAllFlashCards();
        assertEquals(1, allCards.size());
        assertEquals("Question", allCards.getFirst().getQuestion());
    }

    @Test
    void shouldGetAllFlashCards() {
        // ARRANGE
        this.service.saveFlashCard(new FlashCard("Q1", "A1"));
        this.service.saveFlashCard(new FlashCard("Q2", "A2"));
        this.service.saveFlashCard(new FlashCard("Q3", "A3"));

        // ACT
        ArrayList<FlashCard> cards = this.service.getAllFlashCards();

        // ASSERT
        assertEquals(3, cards.size());
    }

    @Test
    void shouldDeleteFlashCardThroughService() {
        // ARRANGE
        FlashCard card = new FlashCard("Delete", "A");
        this.service.saveFlashCard(card);
        int idToDelete = card.getId();

        // ACT
        this.service.deleteFlashCard(idToDelete);

        // ASSERT
        assertTrue(this.service.getAllFlashCards().isEmpty());
    }
}
