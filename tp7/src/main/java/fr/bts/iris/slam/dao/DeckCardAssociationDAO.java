package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.model.FlashCard;

import java.sql.*;
import java.util.ArrayList;

public class DeckCardAssociationDAO {
    private final Connection connection;

    public DeckCardAssociationDAO(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS deck_cards (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                deck_id INT REFERENCES decks(id) ON DELETE CASCADE,
                card_id INT REFERENCES flashcards(id) ON DELETE CASCADE
            )
        """;

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create deck_cards table: " + e.getMessage(), e);
        }
    }

    public void save(int deck_id, int card_id) {
        String sql = "INSERT INTO deck_cards (deck_id, card_id) VALUES (?, ?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, deck_id);
            pstmt.setInt(2, card_id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save deck_cards: " + e.getMessage(), e);
        }
    }


    public ArrayList<Integer> findAllCardsByDeck(int deck_id) {
        ArrayList<Integer> cards_id = new ArrayList<>();
        String sql = "SELECT card_id FROM deck_cards WHERE deck_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, deck_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cards_id.add(
                        rs.getInt("card_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find All failed", e);
        }
        return cards_id;
    }



    public void deleteByAssociation(int deck_id, int card_id) {
        String sql = "DELETE FROM deck_cards WHERE deck_id = ? AND card_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, deck_id);
            pstmt.setInt(2, card_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }

    public void deleteDeckByDeckId(int deck_id) {
        String sql = "DELETE FROM deck_cards WHERE deck_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, deck_id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }

}
