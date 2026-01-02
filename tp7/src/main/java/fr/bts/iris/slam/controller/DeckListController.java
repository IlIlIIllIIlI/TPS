package fr.bts.iris.slam.controller;

import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.service.DeckService;
import fr.bts.iris.slam.service.FlashCardService;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DeckListController {
    @FXML
    private TableColumn<Deck,String> nameColumn;
    @FXML
    private TableColumn<Deck,String> descriptionColumn;
    @FXML
    private TableColumn<Deck,Integer> cardsColumn;
    @FXML
    private TableView<Deck> decksTable;
    @FXML
    private Button manageButton;
    ObservableList<Deck> decks;
    DeckService deckService;
    FlashCardService cardService;

    public void initialize(){
        this.decks = FXCollections.observableArrayList();
        this.decksTable.setItems(this.decks);

        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.cardsColumn.setCellValueFactory( c -> new SimpleIntegerProperty(c.getValue().getCards().size()).asObject());
        BooleanBinding enabled = this.decksTable.getSelectionModel().selectedItemProperty().isNull();
        this.manageButton.disableProperty().bind(enabled);

    }

    public void handleMan(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/deck-composition-dialog.fxml"));
        Parent root = loader.load();
        DeckCompositionController controller = loader.getController();
        controller.initialize();
        controller.setDeckId(this.decksTable.getSelectionModel().getSelectedItem().getId());
        controller.setService(this.deckService,this.cardService);
        controller.checkBoxHandler();
        controller.load();
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(root));
        dialogStage.showAndWait();
        refresh();
    }

    public void setDeckService(DeckService deckService, FlashCardService cardService){
        this.deckService = deckService;
        this.cardService = cardService;
        refresh();
    }

    public void refresh(){
        this.decks.clear();
        this.decks.addAll(this.deckService.loadWithFlashCards());
    }
}
