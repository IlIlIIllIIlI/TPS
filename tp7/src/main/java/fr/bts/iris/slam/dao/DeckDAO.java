package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.Deck;
import fr.bts.iris.slam.model.FlashCard;

import java.sql.*;
import java.util.ArrayList;

public class DeckDAO {
    private final Connection connection;

    public DeckDAO(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS decks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                description TEXT NOT NULL
            )
        """;

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create decks table: " + e.getMessage(), e);
        }
    }


    public void save(Deck deck) {
        String sql = "INSERT INTO decks (name, description) VALUES (?, ?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, deck.getName());
            pstmt.setString(2, deck.getDescription());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    deck.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save deck: " + e.getMessage(), e);
        }
    }


    public ArrayList<Deck> findAll() {
        ArrayList<Deck> decks = new ArrayList<>();
        String sql = "SELECT id, name, description FROM decks ORDER BY name ASC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                decks.add(new Deck (
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find All failed", e);
        }
        return decks;
    }



    public void update(Deck deck) {
        String sql = "UPDATE decks SET name = ?, description= ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, deck.getName());
            pstmt.setString(2, deck.getDescription());
            pstmt.setInt(3, deck.getId());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new IllegalArgumentException("Deck not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Update failed", e);
        }
    }

    public void deleteByName(String name) {
        String sql = "DELETE FROM decks WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }

    public Deck findByIdWithCards(int id)  {
        DeckCardAssociationDAO associationDAO = new DeckCardAssociationDAO(connection);
        FlashCardDAO flashCardDAO = new FlashCardDAO(connection);
        Deck deck = null;
        String sql = "SELECT id, name, description FROM decks WHERE id = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()){
                deck = new Deck (
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
            ArrayList<Integer> cards_id = associationDAO.findAllCardsByDeck(id);
            for (Integer card_id : cards_id){
                deck.addCard(flashCardDAO.findById(card_id));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find All failed", e);
        }
        return deck;
    }
}
