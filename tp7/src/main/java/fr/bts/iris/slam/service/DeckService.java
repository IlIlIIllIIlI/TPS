package fr.bts.iris.slam.service;

import fr.bts.iris.slam.dao.DeckCardAssociationDAO;
import fr.bts.iris.slam.dao.DeckDAO;
import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.model.FlashCard;
import javafx.beans.value.ObservableValue;

import java.util.ArrayList;
import java.util.List;

public class DeckService {
    DeckDAO deckDAO;
    DeckCardAssociationDAO deckCardAssociationDAO;

    public DeckService(DeckDAO deckDAO, DeckCardAssociationDAO deckCardAssociationDAO){
        if (deckDAO == null|| deckCardAssociationDAO == null) {
            throw new IllegalArgumentException("DAO cannot be null");
        }

        this.deckCardAssociationDAO = deckCardAssociationDAO;
        this.deckDAO = deckDAO;
    }

    public void updateDeckComposition(int deckId, List<Integer> cardIds){
        this.deckCardAssociationDAO.deleteDeckByDeckId(deckId);
        for (Integer cardId: cardIds){
            this.deckCardAssociationDAO.save(deckId,cardId);
        }
    }

    public ArrayList<Deck> loadWithFlashCards(){
        ArrayList<Deck> decks = this.deckDAO.findAll();
        ArrayList<Deck> decksWithCard = new ArrayList<>();
        for (Deck deck : decks){
            decksWithCard.add(this.deckDAO.findByIdWithCards(deck.getId()));
        }
        return decksWithCard;
    }

    public boolean isInDeck(int deck_id, int card_id){
        Deck deck = this.deckDAO.findByIdWithCards(deck_id);

        for (FlashCard card : deck.getCards()){
            if (card.getId() == card_id){
                return  true;
            }
        }

        return false;
    }

}
