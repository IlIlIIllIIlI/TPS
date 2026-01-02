package fr.bts.iris.slam.model;

import fr.bts.iris.slam.Difficulty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReviewHistoryTest {
    private ReviewHistory reviewHistory;
    private final int FLASHCARD_ID = 4;
    private final Difficulty DIFFICULTY = Difficulty.MEDIUM;

    @BeforeEach
    void setUp() {
        reviewHistory = new ReviewHistory(FLASHCARD_ID, DIFFICULTY);
    }


    @Test
    void shouldCreateReviewHistoryWithValidParameters() {
        ReviewHistory newHistory = new ReviewHistory(10, Difficulty.EASY);

        assertEquals(10, newHistory.getFlashCardId());
        assertEquals(Difficulty.EASY, newHistory.getDifficulty());
        assertEquals(LocalDate.now(), newHistory.getReviewDate());
    }

    @Test
    void shouldCreateReviewHistoryWithAllParameters() {
        LocalDate specificDate = LocalDate.of(2024, 1, 1);
        ReviewHistory newHistory = new ReviewHistory(1, 10, specificDate, Difficulty.HARD);

        assertEquals(1, newHistory.getId());
        assertEquals(10, newHistory.getFlashCardId());
        assertEquals(specificDate, newHistory.getReviewDate());
        assertEquals(Difficulty.HARD, newHistory.getDifficulty());
    }


    @Test
    void shouldSetId() {
        reviewHistory.setId(1);
        assertEquals(1, reviewHistory.getId());
    }

    @Test
    void shouldSetDifficulty() {
        reviewHistory.setDifficulty(Difficulty.HARD);
        assertEquals(Difficulty.HARD, reviewHistory.getDifficulty());
    }

    @Test
    void shouldGetId() {
        assertEquals(0, reviewHistory.getId());
    }

    @Test
    void shouldGetFlashCardId() {
        assertEquals(FLASHCARD_ID, reviewHistory.getFlashCardId());
    }

    @Test
    void shouldGetReviewDate() {
        assertEquals(LocalDate.now(), reviewHistory.getReviewDate());
    }

    @Test
    void shouldGetDifficulty() {
        assertEquals(DIFFICULTY, reviewHistory.getDifficulty());
    }
}
