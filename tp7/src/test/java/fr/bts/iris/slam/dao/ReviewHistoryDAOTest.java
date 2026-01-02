package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.Difficulty;
import fr.bts.iris.slam.model.ReviewHistory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewHistoryDAOTest {
    private ReviewHistoryDAO reviewHistoryDAO;
    private Connection testConnection;

    @BeforeEach
    void setUp() throws SQLException {
        this.testConnection = DriverManager.getConnection("jdbc:sqlite::memory:");

        this.reviewHistoryDAO = new ReviewHistoryDAO(this.testConnection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.testConnection != null && !this.testConnection.isClosed()) {
            this.testConnection.close();
        }
    }

    @Test
    void ShouldCreateTableReviewHistory(){
        assertDoesNotThrow(() -> new ReviewHistoryDAO(this.testConnection));
    }

    @Test
    void shouldSaveReviewHistoryAndGenerateId(){

        int flashCardId = 1;
        ReviewHistory history = new ReviewHistory(flashCardId, Difficulty.EASY);


        this.reviewHistoryDAO.save(history);


        assertNotEquals(0, history.getId());
    }

    @Test
    void shouldFindHistoryByCardId(){
        ReviewHistory review1 = new ReviewHistory(1, Difficulty.EASY);
        ReviewHistory review2 = new ReviewHistory(1, Difficulty.HARD);
        ReviewHistory review3 = new ReviewHistory(2, Difficulty.MEDIUM);

        this.reviewHistoryDAO.save(review1);
        this.reviewHistoryDAO.save(review2);
        this.reviewHistoryDAO.save(review3);


        List<ReviewHistory> card1 = this.reviewHistoryDAO.findByCardId(1);
        List<ReviewHistory> card2 = this.reviewHistoryDAO.findByCardId(2);
        List<ReviewHistory> card3 = this.reviewHistoryDAO.findByCardId(3);

        assertEquals(2, card1.size());
        assertEquals(1, card2.size());
        assertTrue(card3.isEmpty());
    }

    @Test
    void shouldMapDataCorrectly(){

        int cardId = 5;
        Difficulty diff = Difficulty.HARD;
        ReviewHistory original = new ReviewHistory(cardId, diff);

        this.reviewHistoryDAO.save(original);
        List<ReviewHistory> results = this.reviewHistoryDAO.findByCardId(cardId);
        ReviewHistory retrieved = results.getFirst();

        assertEquals(original.getId(), retrieved.getId());
        assertEquals(original.getFlashCardId(), retrieved.getFlashCardId());
        assertEquals(diff, retrieved.getDifficulty());
        assertEquals(original.getReviewDate(), retrieved.getReviewDate());
    }
}
