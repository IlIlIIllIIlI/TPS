package fr.bts.iris.slam.service;

import fr.bts.iris.slam.Difficulty;
import fr.bts.iris.slam.dao.DeckDAO;
import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.dao.ReviewHistoryDAO;
import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.model.FlashCard;
import fr.bts.iris.slam.model.ReviewHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class RevisionService {
    private final DeckDAO deckDAO;
    private final FlashCardDAO flashCardDAO;
    private final ReviewHistoryDAO reviewHistoryDAO;

    public RevisionService(DeckDAO deckDAO, ReviewHistoryDAO reviewHistoryDAO,FlashCardDAO flashCardDAO) {
        if (deckDAO == null|| reviewHistoryDAO ==null || flashCardDAO == null) {
            throw new IllegalArgumentException("DAO cannot be null");
        }
        this.flashCardDAO = flashCardDAO;
        this.deckDAO = deckDAO;
        this.reviewHistoryDAO = reviewHistoryDAO;
    }

    public ArrayList<FlashCard> getCardsDueForDeck(int deckId) {
        Deck deck = this.deckDAO.findByIdWithCards(deckId);
        ArrayList<FlashCard> cardsToday = new ArrayList<>();

        for (FlashCard card : deck.getCards()){
            if (!card.getNextReviewDate().isAfter(LocalDate.now())) {
                cardsToday.add(card);
            }
        }

        return cardsToday;
    }

    public void processRevision(FlashCard card, Difficulty difficulty) {
        ReviewHistory history = new ReviewHistory(card.getId(), difficulty);
        reviewHistoryDAO.save(history);

        calculateNextReview(card, difficulty);

        flashCardDAO.update(card);


    }

    private void calculateNextReview(FlashCard card, Difficulty difficulty) {
        int currentInterval = card.getIntervalDays();
        double currentEase = card.getEaseFactor();

        int newInterval;
        double newEase = currentEase;


        if (difficulty == Difficulty.EASY){
            newInterval = (int) Math.round(currentInterval * currentEase);
            newEase = currentEase + 0.15;
        } else if (difficulty == Difficulty.MEDIUM) {
            newInterval = (int) Math.round(currentInterval * 1.5);
        } else if (difficulty == Difficulty.HARD) {
            newInterval = 1;
            newEase = currentEase - 0.20;
        }else {
            throw new IllegalArgumentException("Unknown difficulty : " + difficulty);
        }


        if (newEase < 1.3) {
            newEase = 1.3;
        }

        if (newInterval < 1) {
            newInterval = 1;
        }

        card.setEaseFactor(newEase);
        card.setIntervalDays(newInterval);
        card.setNextReviewDate(LocalDate.now().plusDays(newInterval));
    }
}
