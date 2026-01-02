package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.FlashCard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class FlashCardDAOTest {
    private FlashCardDAO flashCardDAO;
    private Connection testConnection;

    @BeforeEach
    void setUp() throws SQLException {
        this.testConnection = DriverManager.getConnection("jdbc:sqlite::memory:");

        this.flashCardDAO = new FlashCardDAO(this.testConnection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.testConnection != null && !this.testConnection.isClosed()) {
            this.testConnection.close();
        }
    }

    @Test
    void ShouldCreateTableFlashcards(){
        assertDoesNotThrow (() -> new FlashCardDAO(this.testConnection));
    }
    @Test
    void shouldInsertFlashCardsAndGenerateId(){
        FlashCard card = new FlashCard("question","answer");

        this.flashCardDAO.save(card);

        assertNotEquals(0, card.getId());
    }

    @Test
    void shouldFindAllFlashCards(){
        FlashCard card1 = new FlashCard("question1","answer1");
        FlashCard card2 = new FlashCard("question2","answer2");
        FlashCard card3 = new FlashCard("question3","answer3");
        FlashCard card4 = new FlashCard("question4","answer4");

        this.flashCardDAO.save(card1);
        this.flashCardDAO.save(card2);
        this.flashCardDAO.save(card3);
        this.flashCardDAO.save(card4);

        assertEquals(4,this.flashCardDAO.findAll().size());
    }

    @Test
    void shouldUpdateExistingFlashCard(){
        FlashCard card = new FlashCard("question1","answer1");
        FlashCard newCard = new FlashCard(1,"question2","answer2");

        this.flashCardDAO.save(card);

        this.flashCardDAO.update(newCard);

        assertEquals("answer2",this.flashCardDAO.findAll().getFirst().getAnswer());
    }

    @Test
    void shouldDeleteFlashCard(){
        FlashCard card = new FlashCard(1,"question1","answer1");


        this.flashCardDAO.save(card);

        this.flashCardDAO.deleteById(1);

        assertTrue(this.flashCardDAO.findAll().isEmpty());

    }

}
