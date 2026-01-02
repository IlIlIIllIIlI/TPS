package fr.bts.iris.slam.service;

import fr.bts.iris.slam.dao.DeckDAO;
import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.model.FlashCard;

import java.util.ArrayList;

public class FlashCardService {
    FlashCardDAO cardDAO;
    public FlashCardService(FlashCardDAO cardDAO){
        if (cardDAO == null) {
            throw new IllegalArgumentException("DAO cannot be null");
        }
        this.cardDAO = cardDAO;
    }

    public ArrayList<FlashCard> getAllFlashCards(){
        return this.cardDAO.findAll();
    }

    public void saveFlashCard(FlashCard card){
        this.cardDAO.save(card);
    }

    public void deleteFlashCard(int id){
        this.cardDAO.deleteById(id);
    }
}
