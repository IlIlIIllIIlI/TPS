package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.Session;
import fr.bts.iris.slam.model.Slide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class SessionDAOTest {
    private SessionDAO sessionDAO;
    private Connection testConnection;

    @BeforeEach
    void setUp() throws SQLException {
        this.testConnection = DriverManager.getConnection("jdbc:sqlite::memory:");

        this.sessionDAO = new SessionDAO(this.testConnection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.testConnection != null && !this.testConnection.isClosed()) {
            this.testConnection.close();
        }
    }

    @Test
    void ShouldCreateTableSessions(){
        assertDoesNotThrow (() -> new SessionDAO(this.testConnection));
    }

    @Test
    void shouldInsertSessionAndGenerateId(){
        Session session = new Session("Session1");

        this.sessionDAO.insert(session);

        assertNotEquals(0, session.getId());
    }

    @Test
    void shouldFindSessionByName(){
        Session session = new Session("Session1");

        this.sessionDAO.save(session);

        assertTrue(this.sessionDAO.findByName("Session1").isPresent());


    }

    @Test
    void shouldReturnEmptyWhenSessionNotFound(){
        Session session = new Session("Session1");

        this.sessionDAO.save(session);

        assertFalse(this.sessionDAO.findByName("Session2").isPresent());


    }

    @Test
    void shouldFindAllSessions(){
        Session session1 = new Session("Session1");
        Session session2 = new Session("Session2");
        Session session3 = new Session("Session3");
        Session session4 = new Session("Session4");

        this.sessionDAO.insert(session1);
        this.sessionDAO.insert(session2);
        this.sessionDAO.insert(session3);
        this.sessionDAO.insert(session4);

        assertEquals(4,this.sessionDAO.findAll().size());
    }

    @Test
    void shouldUpdateExistingSession(){
        Session session = new Session("Session1");
        Session newSession = new Session(1,"Session2");

        this.sessionDAO.insert(session);

        this.sessionDAO.update(newSession);

        assertTrue(this.sessionDAO.findByName("Session2").isPresent());
    }

    @Test
    void shouldDeleteSessionAndCascadeSlides(){
        Session session = new Session("Session1");
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg",1);
        SlideDAO slideDAO = new SlideDAO(this.testConnection);

        this.sessionDAO.insert(session);
        slideDAO.save(slide, 1);

        this.sessionDAO.deleteByName("Session1");

        assertTrue(this.sessionDAO.findByName("Session1").isEmpty());
        assertTrue(slideDAO.findBySessionId(1).isEmpty());
        
    }
    
    @Test
    void shouldSaveSessionWithSlides(){
        Session session = new Session("Session1");
        Slide slide1 = new Slide("Introduction", "#FF0000", "intro.jpg",1);
        Slide slide2 = new Slide("Statistique", "#FF0000", "stats.jpg",2);
        SlideDAO slideDAO = new SlideDAO(this.testConnection);

        session.addSlide(slide1);
        session.addSlide(slide2);

        this.sessionDAO.save(session);

        assertTrue(this.sessionDAO.findByName("Session1").isPresent());
        assertEquals(2,slideDAO.findBySessionId(1).size());

    }
}
