package fr.bts.iris.slam.controller;

import fr.bts.iris.slam.Main;
import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.service.DeckService;
import fr.bts.iris.slam.service.FlashCardService;
import fr.bts.iris.slam.service.RevisionService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button editButton;
    @FXML
    private Button reviewButton;
    private FlashCardDAO flashCardDAO;
    private RevisionService revisionService;
    private DeckService deckService;
    private FlashCardService flashCardService;


        public void initialize(FlashCardDAO flashCardDAO, RevisionService revisionService, DeckService deckService, FlashCardService flashCardService) {
            this.flashCardDAO = flashCardDAO;
            this.revisionService = revisionService;
            this.flashCardService = flashCardService;
            this.deckService = deckService;
        }

        public void handleEditMode() throws IOException {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-view.fxml"));
                Parent root = loader.load();

                MainController controller = loader.getController();
                controller.initialize(this.flashCardDAO,this.deckService,this.flashCardService);

                Stage stage = new Stage();
                stage.setTitle("Mode Édition");
                stage.setScene(new Scene(root));
                stage.show();

        }


        public void handleReviewMode() throws IOException {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/deck-selection-view.fxml"));
                Parent root = loader.load();
                DeckSelectionController controller = loader.getController();
                controller.initialize(deckService,revisionService);
                Stage stage = new Stage();
                stage.setTitle("Mode Révision");
                stage.setScene(new Scene(root));
                stage.show();

        }
}
