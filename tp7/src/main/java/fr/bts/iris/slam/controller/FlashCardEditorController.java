package fr.bts.iris.slam.controller;

import fr.bts.iris.slam.model.FlashCard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FlashCardEditorController {
    @FXML
    private TextField questionField;
    @FXML
    private TextArea answerArea;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    protected boolean confirmed;

    FlashCard card;

    public void initialize(FlashCard card){
        this.card = card;
        if (this.card != null) {
            this.questionField.setText(card.getQuestion());
            this.answerArea.setText(card.getAnswer());

        }
    }

    public FlashCard getCard() {
        return card;
    }

    public void handleConfirm(ActionEvent actionEvent) {
        if (this.questionField.getText() == null||this.questionField.getText().isBlank() ) {
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setContentText("Question is empty !");
            warning.showAndWait();
            return;
        }
        if (this.answerArea.getText() == null||this.answerArea.getText().isBlank() ) {
            Alert warning = new Alert(Alert.AlertType.ERROR);
            warning.setContentText("Answer is empty !");
            warning.showAndWait();
            return;
        }
        this.confirmed = true;
        if (this.card == null) {
            card = new FlashCard(this.questionField.getText(),this.answerArea.getText());
        } else{
            card.setAnswer(this.answerArea.getText());
            card.setQuestion(this.questionField.getText());
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        this.confirmed = false;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }
}
