package fr.bts.iris.slam.model;

import java.time.LocalDate;

public class FlashCard {
    protected  int id;
    protected  String question;
    protected  String answer;

    private LocalDate nextReviewDate;
    private int intervalDays;
    private double easeFactor;

    public FlashCard(String question, String answer){
        if (question == null|| question.isBlank()) {
            throw new IllegalArgumentException("Question cannot be empty or null");
        }
        if (answer == null|| answer.isBlank()) {
            throw new IllegalArgumentException("Answer cannot be empty or null");
        }

        this.answer = answer;
        this.question = question;
        this.id = 0;
        this.intervalDays = 1;
        this.easeFactor = 2.5;
        this.nextReviewDate = LocalDate.now();
    }
    public FlashCard(int id, String question, String answer,LocalDate nextReviewDate,int intervalDays,double easeFactor){
        this.id = id;
        this.answer = answer;
        this.question = question;
        this.nextReviewDate = nextReviewDate;
        this.intervalDays = intervalDays;
        this.easeFactor = easeFactor;
    }

    public FlashCard(int id, String question, String answer) {
        this.id = id;
        this.answer = answer;
        this.question = question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setAnswer(String answer) {
        if (answer == null|| answer.isBlank()) {
            throw new IllegalArgumentException("Answer cannot be empty or null");
        }

        this.answer = answer;
    }

    public void setQuestion(String question) {
        if (question == null|| question.isBlank()) {
            throw new IllegalArgumentException("Question cannot be empty or null");
        }

        this.question = question;
    }

    public LocalDate getNextReviewDate() {
        return this.nextReviewDate;
    }

    public void setNextReviewDate(LocalDate nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }

    public int getIntervalDays() {
        return this.intervalDays;
    }

    public void setIntervalDays(int intervalDays) {
        if (intervalDays <1) {
            throw new IllegalArgumentException("Interval Days cannot be 0 or less");
        }
        this.intervalDays = intervalDays;
    }

    public double getEaseFactor() {
        return this.easeFactor;
    }

    public void setEaseFactor(double easeFactor) {
        if (easeFactor <1.3f) {
            throw new IllegalArgumentException("easeFactor  cannot be < 1.3");
        }
        this.easeFactor = easeFactor;
    }
}
