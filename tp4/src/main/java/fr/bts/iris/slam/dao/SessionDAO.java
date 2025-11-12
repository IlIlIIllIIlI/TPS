package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.Session;
import fr.bts.iris.slam.model.Slide;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionDAO {
    private final Connection connection;

    public SessionDAO(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS sessions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL
            )
        """;

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create sessions table: " + e.getMessage(), e);
        }
    }


    public void save(Session session) {
        if (exists(session.getName())) {
            update(session);
        } else{
            insert(session);
        }

        SlideDAO slideDAO = new SlideDAO(connection);

        for (Slide slide : session.getSlides()) {
            slideDAO.save(slide, session.getId());
        }

    }

    public Optional<Session> findByName(String name) {
        String sql = "SELECT id, name FROM sessions WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return Optional.of(new Session(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException("Find failed", e);
        }
    }

    public List<Session> findAll() {
        List<Session> sessions = new ArrayList<>();
        String sql = "SELECT id, name FROM sessions ORDER BY name";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) { // Permet de parcourir toutes les lignes
                sessions.add(new Session(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Find all failed", e);
        }
        return sessions;
    }

    public void update(Session session) {
        String sql = "UPDATE sessions SET name = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, session.getName());
            pstmt.setInt(2, session.getId());
            int rows = pstmt.executeUpdate();
            if (rows == 0) {
                throw new IllegalArgumentException("Session not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Update failed", e);
        }
    }

    public boolean exists(String name){
        return findByName(name).isPresent();
    }

    public void insert(Session session){
        String sql = "INSERT INTO sessions (name) VALUES (?)";

        try (PreparedStatement pstmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, session.getName());
            pstmt.executeUpdate();

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    session.setId(keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert session: " + e.getMessage(), e);
        }
    }

    public int deleteByName(String name) {
        String sql = "DELETE FROM sessions WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed", e);
        }
    }

    public Optional<Session> findByNameWithSlides(String name) {
        Optional<Session> sessionOpt = findByName(name);
        if (sessionOpt.isEmpty()) return Optional.empty();

        Session session = sessionOpt.get();

        SlideDAO slideDAO = new SlideDAO(connection);
        List<Slide> slides = slideDAO.findBySessionId(session.getId());

        for (Slide slide : slides) {
            session.addSlide(slide);
        }
        return Optional.of(session);
    }
}
