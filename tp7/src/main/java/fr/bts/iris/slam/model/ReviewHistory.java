package fr.bts.iris.slam.model;

import fr.bts.iris.slam.Difficulty;

import java.time.LocalDate;

public class ReviewHistory {
    private int id;
    private int flashCardId;
    private LocalDate reviewDate;
    private Difficulty difficulty;

    public ReviewHistory(int flashCardId, Difficulty difficulty) {
        this.flashCardId = flashCardId;
        this.difficulty = difficulty;
        this.reviewDate = LocalDate.now();
    }

    public ReviewHistory(int id, int flashCardId, LocalDate reviewDate, Difficulty difficulty) {
        this.id = id;
        this.flashCardId = flashCardId;
        this.reviewDate = reviewDate;
        this.difficulty = difficulty;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFlashCardId() { return flashCardId; }

    public LocalDate getReviewDate() { return reviewDate; }

    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }

}
