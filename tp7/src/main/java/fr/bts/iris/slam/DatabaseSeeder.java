package fr.bts.iris.slam;

import java.sql.*;
import java.time.LocalDate;

/**
 * Classe utilitaire pour charger des données de test
 * Détecte automatiquement si la partie 3 (SRS) est implémentée
 */
public class DatabaseSeeder {
    
    private final Connection connection;
    private boolean hasSRSColumns = false;
    
    public DatabaseSeeder(Connection connection) {
        this.connection = connection;
        detectSRSColumns();
    }
    
    /**
     * Détecte si les colonnes SRS (partie 3) existent dans la table flashcards
     */
    private void detectSRSColumns() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "flashcards", null);
            
            boolean hasNextReview = false;
            boolean hasInterval = false;
            boolean hasEaseFactor = false;
            
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if ("next_review_date".equals(columnName)) hasNextReview = true;
                if ("interval_days".equals(columnName)) hasInterval = true;
                if ("ease_factor".equals(columnName)) hasEaseFactor = true;
            }
            
            hasSRSColumns = hasNextReview && hasInterval && hasEaseFactor;
            
        } catch (SQLException e) {
            hasSRSColumns = false;
        }
    }
    
    /**
     * Insère une flashcard avec détection automatique du schéma
     */
    private void insertFlashcard(String question, String answer) throws SQLException {
        String sql;
        if (hasSRSColumns) {
            sql = "INSERT INTO flashcards (question, answer, next_review_date, interval_days, ease_factor) VALUES (?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO flashcards (question, answer) VALUES (?, ?)";
        }
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, question);
            pstmt.setString(2, answer);
            
            if (hasSRSColumns) {
                pstmt.setString(3, LocalDate.now().toString());
                pstmt.setInt(4, 1);
                pstmt.setDouble(5, 2.5);
            }
            
            pstmt.execute();
        }
    }
    
    /**
     * Remplit la base avec 30 flashcards d'exemple (Partie 1)
     */
    public void seedFlashcards() {
        try (Statement stmt = connection.createStatement()) {
            // Vérifier que la table flashcards existe
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "flashcards", null);
            if (!tables.next()) {
                return;
            }
            
            // Nettoyer
            stmt.execute("DELETE FROM flashcards");
            stmt.execute("DELETE FROM sqlite_sequence WHERE name='flashcards'");
            
            // Java - Fondamentaux (10 cartes)
            insertFlashcard("Qu'est-ce qu'une variable en Java ?", 
                "Un conteneur qui stocke une valeur d'un certain type");
            
            insertFlashcard("Quelle est la différence entre int et Integer ?", 
                "int est un type primitif, Integer est une classe wrapper qui encapsule un int");
            
            insertFlashcard("Qu'est-ce qu'une classe ?", 
                "Un modèle qui définit les attributs et méthodes d'un objet");
            
            insertFlashcard("Qu'est-ce qu'un objet ?", 
                "Une instance d'une classe, créée avec le mot-clé new");
            
            insertFlashcard("À quoi sert une méthode ?", 
                "À définir un comportement ou une action que peut effectuer un objet");
            
            insertFlashcard("Qu'est-ce qu'un attribut ?", 
                "Une variable qui appartient à une classe et représente une propriété d'un objet");
            
            insertFlashcard("Que fait le mot-clé this ?", 
                "Il fait référence à l'instance courante de la classe");
            
            insertFlashcard("Qu'est-ce qu'un constructeur ?", 
                "Une méthode spéciale appelée lors de la création d'un objet pour l'initialiser");
            
            insertFlashcard("À quoi sert la boucle for ?", 
                "À répéter un bloc de code un nombre déterminé de fois");
            
            insertFlashcard("Qu'est-ce qu'une condition if ?", 
                "Une instruction qui exécute du code uniquement si une condition est vraie");
            
            // SQL - Fondamentaux (10 cartes)
            insertFlashcard("À quoi sert la commande SELECT ?", 
                "À récupérer des données depuis une table de base de données");
            
            insertFlashcard("À quoi sert la commande INSERT ?", 
                "À ajouter de nouvelles lignes dans une table");
            
            insertFlashcard("À quoi sert la commande UPDATE ?", 
                "À modifier des données existantes dans une table");
            
            insertFlashcard("À quoi sert la commande DELETE ?", 
                "À supprimer des lignes d'une table");
            
            insertFlashcard("Qu'est-ce qu'une table ?", 
                "Une structure qui organise les données en lignes et colonnes");
            
            insertFlashcard("Qu'est-ce qu'une clé primaire ?", 
                "Une colonne qui identifie de manière unique chaque ligne d'une table");
            
            insertFlashcard("À quoi sert la clause WHERE ?", 
                "À filtrer les résultats d'une requête selon une condition");
            
            insertFlashcard("À quoi sert la clause ORDER BY ?", 
                "À trier les résultats d'une requête selon une ou plusieurs colonnes");
            
            insertFlashcard("Qu'est-ce qu'une colonne ?", 
                "Un attribut ou champ qui stocke un type de donnée spécifique dans une table");
            
            insertFlashcard("À quoi sert CREATE TABLE ?", 
                "À créer une nouvelle table dans la base de données");
            
            // JavaFX - Fondamentaux (10 cartes)
            insertFlashcard("Qu'est-ce qu'un Stage en JavaFX ?", 
                "La fenêtre principale de l'application");
            
            insertFlashcard("Qu'est-ce qu'une Scene en JavaFX ?", 
                "Le contenu affiché dans une fenêtre (Stage)");
            
            insertFlashcard("Qu'est-ce qu'un Button ?", 
                "Un composant cliquable qui déclenche une action");
            
            insertFlashcard("Qu'est-ce qu'un Label ?", 
                "Un composant qui affiche du texte non modifiable");
            
            insertFlashcard("Qu'est-ce qu'un TextField ?", 
                "Un composant qui permet à l'utilisateur de saisir du texte");
            
            insertFlashcard("À quoi sert un VBox ?", 
                "À organiser les composants verticalement (les uns sous les autres)");
            
            insertFlashcard("À quoi sert un HBox ?", 
                "À organiser les composants horizontalement (côte à côte)");
            
            insertFlashcard("Qu'est-ce qu'un fichier FXML ?", 
                "Un fichier XML qui décrit l'interface graphique de façon déclarative");
            
            insertFlashcard("À quoi sert un Controller en JavaFX ?", 
                "À gérer la logique et les événements d'une vue FXML");
            
            insertFlashcard("À quoi sert l'annotation @FXML ?", 
                "À injecter automatiquement les composants du FXML dans le contrôleur");
            
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du remplissage des flashcards", e);
        }
    }
    
    /**
     * Remplit la base avec 3 decks et les associations (Partie 2)
     */
    public void seedDecks() {
        try (Statement stmt = connection.createStatement()) {
            // Vérifier que la table decks existe
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "decks", null);
            if (!tables.next()) {
                return;
            }
            tables = metaData.getTables(null, null, "deck_cards", null);
            if (!tables.next()) {
                return;
            }
            
            // Nettoyer les decks et associations
            stmt.execute("DELETE FROM deck_cards");
            stmt.execute("DELETE FROM decks");
            stmt.execute("DELETE FROM sqlite_sequence WHERE name='decks'");
            stmt.execute("DELETE FROM sqlite_sequence WHERE name='deck_cards'");
            
            // Créer 3 decks
            stmt.execute("INSERT INTO decks (name, description) VALUES ('Java Fondamentaux', 'Les concepts de base de la programmation Java')");
            stmt.execute("INSERT INTO decks (name, description) VALUES ('SQL Essentiel', 'Les commandes et concepts fondamentaux de SQL')");
            stmt.execute("INSERT INTO decks (name, description) VALUES ('JavaFX Interface', 'Les composants de base pour créer des interfaces graphiques')");
            
            // Associations deck_cards
            // Les IDs de flashcards sont de 1 à 30 après seedFlashcards()
            
            // Deck 1 (Java) : cartes 1-10 + carte 29 (Controller - commune avec JavaFX)
            for (int i = 1; i <= 10; i++) {
                stmt.execute("INSERT INTO deck_cards (deck_id, card_id) VALUES (1, " + i + ")");
            }
            stmt.execute("INSERT INTO deck_cards (deck_id, card_id) VALUES (1, 29)");
            
            // Deck 2 (SQL) : cartes 11-20 + carte 15 (clé primaire - commune avec Java/OOP)
            for (int i = 11; i <= 20; i++) {
                stmt.execute("INSERT INTO deck_cards (deck_id, card_id) VALUES (2, " + i + ")");
            }
            
            // Deck 3 (JavaFX) : cartes 21-30 + carte 3 (Classe - commune avec Java)
            for (int i = 21; i <= 30; i++) {
                stmt.execute("INSERT INTO deck_cards (deck_id, card_id) VALUES (3, " + i + ")");
            }
            stmt.execute("INSERT INTO deck_cards (deck_id, card_id) VALUES (3, 3)");
            
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du remplissage des decks", e);
        }
    }
    
    /**
     * Remplit toute la base (flashcards + decks)
     */
    public void seedAll() {
        seedFlashcards();
        seedDecks();
    }
}