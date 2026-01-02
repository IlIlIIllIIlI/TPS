package fr.bts.iris.slam.controller;

import fr.bts.iris.slam.Difficulty;
import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.model.FlashCard;
import fr.bts.iris.slam.service.DeckService;
import fr.bts.iris.slam.service.FlashCardService;
import fr.bts.iris.slam.service.RevisionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RevisionController {
    @FXML
    private Label deckTitleLabel;
    @FXML
    private Label progressLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private VBox answerBox;
    @FXML
    private Label answerLabel;
    @FXML
    private Button showAnswerButton;
    @FXML
    private HBox difficultyBox;
    private RevisionService revisionService;
    private ArrayList<FlashCard> cards;
    private int index = 0;

    public void initialize(RevisionService revisionService, Deck deck)  {
        this.revisionService = revisionService;

        this.cards = revisionService.getCardsDueForDeck(deck.getId());

        this.deckTitleLabel.setText("Deck : " + deck.getName());

        if (cards.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Aucune carte à réviser aujourd'hui pour ce deck !");
            alert.showAndWait();
            Stage stage = (Stage) deckTitleLabel.getScene().getWindow();
            stage.close();
        } else {
            FlashCard card = cards.get(index);
            progressLabel.setText(String.format("Carte %d / %d", index + 1, cards.size()));
            questionLabel.setText(card.getQuestion());
            answerLabel.setText(card.getAnswer());


            answerBox.setVisible(false);

            showAnswerButton.setDisable(false);
            showAnswerButton.setVisible(true);
            difficultyBox.setDisable(true);
        }
    }

    public void handleHard(ActionEvent actionEvent) {
        processReview(Difficulty.HARD);
    }

    public void handleMedium(ActionEvent actionEvent) {
        processReview(Difficulty.MEDIUM);
    }

    public void handleEasy(ActionEvent actionEvent) {
        processReview(Difficulty.EASY);
    }

    public void handleShowAnswer(ActionEvent actionEvent) {
        answerBox.setVisible(true);

        showAnswerButton.setDisable(true);
        difficultyBox.setDisable(false);
    }

    private void processReview(Difficulty difficulty){
        revisionService.processRevision(cards.get(index), difficulty);

        index++;

        if (index < cards.size()) {
            FlashCard card = cards.get(index);
            progressLabel.setText(String.format("Carte %d / %d", index + 1, cards.size()));
            questionLabel.setText(card.getQuestion());
            answerLabel.setText(card.getAnswer());


            answerBox.setVisible(false);

            showAnswerButton.setDisable(false);
            showAnswerButton.setVisible(true);
            difficultyBox.setDisable(true);
        } else {
            questionLabel.setText("Session terminée !");
            answerLabel.setText("Bravo, vous avez révisé toutes les cartes.");
            answerBox.setVisible(true);
            showAnswerButton.setVisible(false);
            difficultyBox.setDisable(true);
            Stage stage = (Stage) deckTitleLabel.getScene().getWindow();
            stage.close();
        }
    }
}
