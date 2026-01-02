package fr.bts.iris.slam.controller;

import fr.bts.iris.slam.model.FlashCard;
import fr.bts.iris.slam.service.DeckService;
import fr.bts.iris.slam.service.FlashCardService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckCompositionController {
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TableView<FlashCard> flashcardsTable;
    @FXML
    private TableColumn<FlashCard, String> questionColumn;
    @FXML
    private TableColumn<FlashCard, String> answerColumn;
    @FXML
    private TableColumn<FlashCard, Boolean> inDeckColumn;
    ObservableList<FlashCard> cards;
    private final HashMap<Integer, BooleanProperty> checkboxStateMap = new HashMap<>();
    DeckService deckService;
    FlashCardService cardService;
    int deckId;


    public void initialize() {
        this.cards = FXCollections.observableArrayList();
        this.flashcardsTable.setItems(this.cards);

        this.questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        this.answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));

    }

    public void setService(DeckService deckservice,FlashCardService cardService) {
        this.deckService = deckservice;
        this.cardService = cardService;
    }

    public void setDeckId(int deckId){
        this.deckId = deckId;
    }

    public void  checkBoxHandler(){
        this.inDeckColumn.setCellFactory(CheckBoxTableCell.forTableColumn(this.inDeckColumn));
        this.inDeckColumn.setCellValueFactory(c -> {
            if (!checkboxStateMap.containsKey(c.getValue().getId())){
                checkboxStateMap.put(c.getValue().getId(),new SimpleBooleanProperty(this.deckService.isInDeck(this.deckId,c.getValue().getId())));
            }
            return checkboxStateMap.get(c.getValue().getId());
        }
    );
    }

    public void load(){
        this.cards.clear();
        this.cards.addAll(this.cardService.getAllFlashCards());
    }
    public void handleConfirm(ActionEvent actionEvent) {
        List<Integer> checkedCardIds = new ArrayList<>();
        for (FlashCard card : this.cards) {
            if (checkboxStateMap.containsKey(card.getId())){
                if (checkboxStateMap.get(card.getId()).get()){
                    checkedCardIds.add(card.getId());
                }
            }else {
                if (this.deckService.isInDeck(this.deckId, card.getId())) {
                    checkedCardIds.add(card.getId());
                }
            }
        }

        System.out.println(checkedCardIds.size());
        this.deckService.updateDeckComposition(deckId,checkedCardIds);

    }

}
