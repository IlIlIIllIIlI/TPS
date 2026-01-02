package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.Difficulty;
import fr.bts.iris.slam.model.ReviewHistory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReviewHistoryDAO {
    private final Connection connection;

    public ReviewHistoryDAO(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS review_history (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                flashcard_id INTEGER NOT NULL,
                review_date TEXT NOT NULL,
                difficulty TEXT NOT NULL,
                FOREIGN KEY (flashcard_id) REFERENCES flashcards(id) ON DELETE CASCADE
            )
        """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create review_history table: " + e.getMessage(), e);
        }
    }
    public void save(ReviewHistory history) {
        String sql = "INSERT INTO review_history (flashcard_id, review_date, difficulty) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, history.getFlashCardId());
            pstmt.setString(2, history.getReviewDate().toString());

            pstmt.setString(3, history.getDifficulty().name());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) history.setId(keys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving history", e);
        }
    }
    public List<ReviewHistory> findByCardId(int cardId) {
        List<ReviewHistory> list = new ArrayList<>();
        String sql = "SELECT * FROM review_history WHERE flashcard_id = ? ORDER BY review_date DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, cardId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new ReviewHistory(
                        rs.getInt("id"),
                        rs.getInt("flashcard_id"),
                        LocalDate.parse(rs.getString("review_date")),
                        Difficulty.fromString(rs.getString("difficulty"))
                ));
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
        return list;
    }
}
