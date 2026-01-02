package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.FlashCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class FlashCardDAO {
    private final Connection connection;

    public FlashCardDAO(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS flashcards (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                question TEXT NOT NULL,
                answer TEXT NOT NULL,
                next_review_date DATE DEFAULT CURRENT_DATE,
                interval_days INTEGER DEFAULT 1,
                ease_factor REAL DEFAULT 2.5
            )
        """;

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create flashcards table: " + e.getMessage(), e);
        }
    }


    public void save(FlashCard card) {
        String sql = "INSERT INTO flashcards (question, answer,next_review_date,interval_days,ease_factor) VALUES (?, ?,?,?,?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, card.getQuestion());
            pstmt.setString(2, card.getAnswer());
            pstmt.setString(3, Date.valueOf(card.getNextReviewDate()).toString());
            pstmt.setInt(4, card.getIntervalDays());
            pstmt.setDouble(5, card.getEaseFactor());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    card.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save card: " + e.getMessage(), e);
        }
    }


    public ArrayList<FlashCard> findAll() {
        ArrayList<FlashCard> cards = new ArrayList<>();
        String sql = "SELECT * FROM flashcards ORDER BY question ASC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                cards.add(new FlashCard (
                        rs.getInt("id"),
                        rs.getString("question"),
                        rs.getString("answer"),
                        LocalDate.parse(rs.getString("next_review_date")),
                        rs.getInt("interval_days"),
                        rs.getDouble("ease_factor")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find All failed", e);
        }
        return cards;
    }



    public void update(FlashCard flashCard) {
        String sql = "UPDATE flashcards SET question = ?, answer= ?, next_review_date = ?, interval_days = ?, ease_factor = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, flashCard.getQuestion());
            pstmt.setString(2, flashCard.getAnswer());
            pstmt.setString(3, flashCard.getNextReviewDate().toString());
            pstmt.setInt(4, flashCard.getIntervalDays());
            pstmt.setDouble(5, flashCard.getEaseFactor());
            pstmt.setInt(6, flashCard.getId());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new IllegalArgumentException("FlashCard not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Update failed", e);
        }
    }

    public void deleteById(int id) {
        String sql = "DELETE FROM flashcards WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }

    public FlashCard findById(int id) {
        FlashCard card;
        String sql = "SELECT * FROM flashcards WHERE id = ? ";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
                card = new FlashCard (
                        rs.getInt("id"),
                        rs.getString("question"),
                        rs.getString("answer"),
                        LocalDate.parse(rs.getString("next_review_date")),
                        rs.getInt("interval_days"),
                        rs.getDouble("ease_factor")
                );
        } catch (SQLException e) {
            throw new RuntimeException("Find All failed", e);
        }
        return card;
    }
}