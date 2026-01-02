package fr.bts.iris.slam.controller;

import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.model.FlashCard;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class FlashCardListController {
    @FXML
    private TableView<FlashCard> flashcardsTable;
    @FXML
    private TableColumn<FlashCard,String> questionColumn;
    @FXML
    private TableColumn<FlashCard, String> answerColumn;
    @FXML private Button editButton;
    @FXML private Button deleteButton;
    ObservableList<FlashCard> cards;
    FlashCardDAO dao;

    public void initialize(){
        this.cards = FXCollections.observableArrayList();
        this.flashcardsTable.setItems(this.cards);

        this.questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
        this.answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));

        BooleanBinding enabled = this.flashcardsTable.getSelectionModel().selectedItemProperty().isNull();
        this.editButton.disableProperty().bind(enabled);
        this.deleteButton.disableProperty().bind(enabled);

    }
    public void handleNew(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/flashcard-editor-dialog.fxml"));
        Parent root = loader.load();
        FlashCardEditorController controller = loader.getController();
        controller.initialize(null);
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(root));
        dialogStage.showAndWait();
        if (controller.isConfirmed()){
            this.dao.save(controller.getCard());
            refresh();

        }
    }

    public void handleEdit(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/flashcard-editor-dialog.fxml"));
        Parent root = loader.load();
        FlashCardEditorController controller = loader.getController();
        controller.initialize(this.flashcardsTable.getSelectionModel().selectedItemProperty().get());
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setScene(new Scene(root));
        dialogStage.showAndWait();
        if (controller.isConfirmed()){
            this.dao.update(controller.getCard());
            refresh();
        }
    }

    public void handleDelete(ActionEvent actionEvent) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setContentText("Voulez-vous vraiment supprimer ?");
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                this.dao.deleteById(this.flashcardsTable.getSelectionModel().selectedItemProperty().get().getId());
                this.flashcardsTable.getItems().remove(this.flashcardsTable.getSelectionModel().getSelectedItem());
            }
        });
    }

    public void setDAO(FlashCardDAO dao){
        this.dao = dao;
        refresh();
    }

    public void refresh(){
        this.cards.clear();
        this.cards.addAll(this.dao.findAll());
    }
}
