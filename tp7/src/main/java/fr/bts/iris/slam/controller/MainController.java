package fr.bts.iris.slam.controller;

import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.service.DeckService;
import fr.bts.iris.slam.service.FlashCardService;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane flashcardsTab;
    @FXML
    private BorderPane decksTab;
    @FXML
    private FlashCardListController flashCardListController;
    @FXML
    private DeckListController deckListController;


    public void initialize(FlashCardDAO flashCardDAO, DeckService deckService, FlashCardService flashCardService) throws IOException {
        flashCardListController.setDAO(flashCardDAO);
        deckListController.setDeckService(deckService,flashCardService);

    }
}
