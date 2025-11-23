package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.Slide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;
import java.util.ArrayList;

public class SlideDAO {
    private static final Logger log = LoggerFactory.getLogger(SlideDAO.class);
    private final Connection connection;

    public SlideDAO(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS slides (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                title_color TEXT NOT NULL,
                image_path TEXT NOT NULL,
                position INTEGER NOT NULL,
                session_id INTEGER NOT NULL,
                FOREIGN KEY (session_id) REFERENCES sessions(id) ON DELETE CASCADE
            )
        """;

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create slides table: " + e.getMessage(), e);
        }
    }


    public void save(Slide slide, int sessionId) {
        String sql = "INSERT INTO slides (title, title_color, image_path, position, session_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, slide.getTitle());
            pstmt.setString(2, slide.getTitleColor());
            pstmt.setString(3, slide.getImagePath());
            pstmt.setInt(4, slide.getPosition());
            pstmt.setInt(5, sessionId);
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    slide.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save slide: " + e.getMessage(), e);
        }
    }

    public ArrayList<Slide> findBySessionId(int sessionId) {
        ArrayList<Slide> slides = new ArrayList<>();
        String sql = "SELECT id, title, title_color, image_path, position, session_id FROM slides WHERE session_id = ? ORDER BY position ASC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, sessionId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                slides.add(new Slide (
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("title_color"),
                        rs.getString("image_path"),
                        rs.getInt("position")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find by SessionId failed", e);
        }
        return slides;
    }

    public int deleteBySessionId(int sessionId) {
        String sql = "DELETE FROM slides WHERE session_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, sessionId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }
}