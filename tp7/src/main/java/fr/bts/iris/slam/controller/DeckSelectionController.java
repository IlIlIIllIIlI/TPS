package fr.bts.iris.slam.controller;

import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.service.DeckService;
import fr.bts.iris.slam.service.RevisionService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeckSelectionController {
    @FXML
    private TableColumn<Deck,Integer> countColumn;
    @FXML
    private TableColumn<Deck,String> nameColumn;
    @FXML
    private TableView<Deck> deckTable;
    @FXML
    private Button startReviewButton;

    ArrayList<Deck> decks;

    private DeckService deckService;
    private RevisionService revisionService;

    private final Map<Integer, Integer> deckCounts = new HashMap<>();

    public void initialize(DeckService deckService, RevisionService revisionService) {
        this.deckService = deckService;
        this.revisionService = revisionService;
        this.decks = this.deckService.loadWithFlashCards();
        ObservableList<Deck> deckObs =  FXCollections.observableArrayList();
        deckObs.addAll(decks);
        this.deckTable.setItems(deckObs);
        deckCounts.clear();

        for (Deck deck : decks) {
            int count = revisionService.getCardsDueForDeck(deck.getId()).size();
            deckCounts.put(deck.getId(), count);
        }


        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));


        countColumn.setCellValueFactory(c -> {
            Deck deck = c.getValue();
            Integer count = deckCounts.get(deck.getId());
            return new SimpleIntegerProperty(count).asObject();
        });


    }

    public void handleStartReview(ActionEvent actionEvent) throws IOException {
        Deck selectedDeck = deckTable.getSelectionModel().getSelectedItem();

        if (selectedDeck == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Veuillez sélectionner un deck.");
            alert.showAndWait();
            return;
        }

        int count = deckCounts.getOrDefault(selectedDeck.getId(), 0);

        if (count == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Aucune carte à réviser pour ce deck aujourd'hui !");
            alert.showAndWait();
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/revision-view.fxml"));
        Parent root = loader.load();

        RevisionController controller = loader.getController();
        controller.initialize(this.revisionService,selectedDeck);

        Stage stage = new Stage();
        stage.setTitle("Révision : " + selectedDeck.getName());
        stage.setScene(new Scene(root));
        stage.show();

        ((Stage) deckTable.getScene().getWindow()).close();


    }
}
