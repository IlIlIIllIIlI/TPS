package fr.bts.iris.slam;

import fr.bts.iris.slam.controller.FlashCardListController;
import fr.bts.iris.slam.controller.MainController;
import fr.bts.iris.slam.controller.WelcomeController;
import fr.bts.iris.slam.dao.DeckCardAssociationDAO;
import fr.bts.iris.slam.dao.DeckDAO;
import fr.bts.iris.slam.dao.FlashCardDAO;
import fr.bts.iris.slam.dao.ReviewHistoryDAO;
import fr.bts.iris.slam.service.DeckService;
import fr.bts.iris.slam.service.FlashCardService;
import fr.bts.iris.slam.service.RevisionService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application {
    public static void main(String[] args) {
            launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:flashcard.db");
        FlashCardDAO flashCardDAO = new FlashCardDAO(conn);
        DeckDAO deckDAO = new DeckDAO(conn);
        DeckCardAssociationDAO deckCardAssociationDAO = new DeckCardAssociationDAO(conn);
        ReviewHistoryDAO reviewHistoryDAO = new ReviewHistoryDAO(conn);
        DeckService deckService = new DeckService(deckDAO,deckCardAssociationDAO);
        FlashCardService flashCardService = new FlashCardService(flashCardDAO);
        RevisionService revisionService = new RevisionService(deckDAO,reviewHistoryDAO,flashCardDAO);
        DatabaseSeeder seeder = new DatabaseSeeder(conn);
        seeder.seedAll();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/welcome-view.fxml"));
        Parent root = loader.load();
        WelcomeController controller = loader.getController();
        controller.initialize(flashCardDAO,revisionService,deckService,flashCardService);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome");
        primaryStage.show();

    }
}
