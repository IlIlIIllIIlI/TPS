package fr.bts.iris.slam.dao;

import fr.bts.iris.slam.model.Session;
import fr.bts.iris.slam.model.Slide;
import org.junit.jupiter.api.*;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class SlideDAOTest {

    private SlideDAO slideDAO;
    SessionDAO sessionDAO;
    private Connection testConnection;
    private static final int TEST_SESSION_ID = 1;

    @BeforeEach
    void setUp() throws SQLException {
        this.testConnection = DriverManager.getConnection("jdbc:sqlite::memory:");

        // Activer les FOREIGN KEY constraints (désactivées par défaut en SQLite)
        Statement stmt = this.testConnection.createStatement();
        stmt.execute("PRAGMA foreign_keys = ON");

         this.sessionDAO = new SessionDAO(this.testConnection);

        this.slideDAO = new SlideDAO(this.testConnection);

        this.sessionDAO.save(new Session(TEST_SESSION_ID,"Session1"));
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (this.testConnection != null && !this.testConnection.isClosed()) {
            this.testConnection.close();
        }
    }

    @Test
    void shouldSaveSlideWithPosition() {
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg",1);

        this.slideDAO.save(slide, TEST_SESSION_ID);

        assertNotEquals(0, slide.getId());

        try (Statement stmt = this.testConnection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM slides WHERE id = " + slide.getId())) {

            assertTrue(rs.next());
            assertEquals("Introduction", rs.getString("title"));
            assertEquals(1, rs.getInt("position"));
            assertEquals(TEST_SESSION_ID, rs.getInt("session_id"));
        } catch (SQLException e) {
            fail("Erreur SQL : " + e.getMessage());
        }
    }

    @Test
    void shouldFindSlidesBySessionId(){
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg",1);

        this.slideDAO.save(slide, TEST_SESSION_ID);



        assertEquals(slide.getTitle(),this.slideDAO.findBySessionId(TEST_SESSION_ID).getFirst().getTitle());
    }

    @Test
    void shouldReturnSlidesInOrder(){
        Slide slide1 = new Slide("Introduction", "#FF0000", "intro.jpg",1);
        Slide slide2 = new Slide("Statistique", "#FF0000", "stats.jpg",3);
        Slide slide3 = new Slide("Content", "#FF0000", "content.jpg",2);
        Slide slide4 = new Slide("Conclusion", "#FF0000", "conclu.jpg",5);
        Slide slide5 = new Slide("Remerciement", "#FF0000", "merci.jpg",4);

        this.slideDAO.save(slide1, TEST_SESSION_ID);
        this.slideDAO.save(slide2, TEST_SESSION_ID);
        this.slideDAO.save(slide3, TEST_SESSION_ID);
        this.slideDAO.save(slide4, TEST_SESSION_ID);
        this.slideDAO.save(slide5, TEST_SESSION_ID);

        assertEquals(slide1.getTitle(),this.slideDAO.findBySessionId(TEST_SESSION_ID).getFirst().getTitle());
        assertEquals(slide3.getTitle(),this.slideDAO.findBySessionId(TEST_SESSION_ID).get(1).getTitle());
        assertEquals(slide2.getTitle(),this.slideDAO.findBySessionId(TEST_SESSION_ID).get(2).getTitle());
        assertEquals(slide5.getTitle(),this.slideDAO.findBySessionId(TEST_SESSION_ID).get(3).getTitle());
        assertEquals(slide4.getTitle(),this.slideDAO.findBySessionId(TEST_SESSION_ID).getLast().getTitle());

    }

    @Test
    void shouldReturnEmptyListForNonExistentSession(){
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg",1);

        this.slideDAO.save(slide, TEST_SESSION_ID);

        assertTrue(this.slideDAO.findBySessionId(TEST_SESSION_ID+1).isEmpty());
    }
    @Test
    void shouldDeleteSlidesBySessionId(){
        Slide slide = new Slide("Introduction", "#FF0000", "intro.jpg",1);
        Slide slide2 = new Slide("Content", "#FF0000", "content.jpg",2);

        this.slideDAO.save(slide, TEST_SESSION_ID);
        this.slideDAO.save(slide2, TEST_SESSION_ID);

        this.slideDAO.deleteBySessionId(TEST_SESSION_ID);

        assertTrue(this.slideDAO.findBySessionId(TEST_SESSION_ID).isEmpty());

    }
}