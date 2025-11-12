package fr.bts.iris.slam.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {
    private Session session;
    private final String NAME = "Bonjour";
    private Slide slide;;
    private final String TITLE_COLOR = "#AAAAAA";
    private final String PATH = "yey.gif";
    private final int POSITION = 3;



    @BeforeEach
    void setUp() {
        session = new Session(NAME);
        slide = new Slide(NAME, TITLE_COLOR, PATH,POSITION);

    }

// === TESTS DE CONSTRUCTION ===

    @Test
    void shouldCreateSessionWithValidParameters() {
        Session newSession = new Session("Aurevoir");

        // ASSERT - Vérification de l'état initial
        assertEquals("Aurevoir", newSession.getName());
        assertEquals(0, newSession.getId());
        assertEquals(0,newSession.getSlideCount());
    }

    @Test
    void shouldCreateSessionWithIdAndValidParameters() {
        Session newSession = new Session(2,"Aurevoir");

        // ASSERT - Vérification de l'état initial
        assertEquals("Aurevoir", newSession.getName());
        assertEquals(2, newSession.getId());
        assertEquals(0,newSession.getSlideCount());
    }

    @Test
    void shouldRejectEmptyName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session(" ")
                // <- lambda function
        );
        assertEquals("Session name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectNullName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session(null)
                // <- lambda function
        );
        assertEquals("Session name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectShortName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session("ds")
                // <- lambda function
        );
        assertEquals("Session name must be at least 3 characters long", exception.getMessage());
    }


    @Test
    void shouldRejectLongName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session("Session name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 characters")
                // <- lambda function
        );
        assertEquals("Session name cannot exceed 50 characters", exception.getMessage());
    }

    @Test
    void shouldRejectSpecialCharName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Session("ijfizj!?.:pd")
                // <- lambda function
        );
        assertEquals("Session name contains invalid characters", exception.getMessage());
    }

    @Test
    void shouldRejectNullAddSlide() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.addSlide(null)
                // <- lambda function
        );
        assertEquals("Slide cannot be null", exception.getMessage());
    }

    @Test
    void shouldAddSlide() {

        session.addSlide(slide);
        assertEquals(slide.getTitle(),session.getSlide(0).getTitle());
    }


    @Test
    void shouldRejectNullRemoveSlide() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.removeSlide(null)
                // <- lambda function
        );
        assertEquals("Slide cannot be null", exception.getMessage());
    }

    @Test
    void shouldRemoveSlide() {
        session.addSlide(slide);
        session.removeSlide(slide);

        assertEquals(0,session.getSlideCount());
    }

    @Test
    void shouldRejectNegativeRemoveSlideAt() {
        // Vérifier qu'une IndexOutOfBoundsException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> session.removeSlideAt(-8)
                // <- lambda function
        );
        assertEquals("Invalid slide index: "+-8, exception.getMessage());
    }

    @Test
    void shouldRejectOutOfBoundRemoveSlideAt() {
        // Vérifier qu'une IndexOutOfBoundsException est levée
        // avec un message approprié

        session.addSlide(slide);

        // ACT & ASSERT : l'action et la vérification en une fois
        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> session.removeSlideAt(8)
                // <- lambda function
        );
        assertEquals("Invalid slide index: "+8, exception.getMessage());
    }

    @Test
    void shouldRemoveSlideAt() {
        session.addSlide(slide);
        session.removeSlideAt(0);

        assertEquals(0,session.getSlideCount());
    }

    @Test
    void shouldRejectNegativeGetSlide() {
        // Vérifier qu'une IndexOutOfBoundsException est levée
        // avec un message approprié

        session.addSlide(slide);

        // ACT & ASSERT : l'action et la vérification en une fois
        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> session.getSlide(-8)
                // <- lambda function
        );
        assertEquals("Invalid slide index: "+-8, exception.getMessage());
    }

    @Test
    void shouldRejectOutOfBoundGetSlide() {
        // Vérifier qu'une IndexOutOfBoundsException est levée
        // avec un message approprié

        session.addSlide(slide);

        // ACT & ASSERT : l'action et la vérification en une fois
        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> session.getSlide(8)
                // <- lambda function
        );
        assertEquals("Invalid slide index: "+8, exception.getMessage());
    }

    @Test
    void shouldGetSlide() {
        session.addSlide(slide);
        session.getSlide(0);

        assertEquals("Bonjour",session.getSlide(0).getTitle());
    }

    @Test
    void shouldGetSlides() {
        session.addSlide(slide);
        session.addSlide(slide);


        assertEquals( session.getSlides().size(),session.getSlideCount());
    }

    @Test
    void shouldGetSlidesWithoutChangingSession() {
        session.addSlide(slide);
        session.addSlide(slide);

        List<Slide> slides= session.getSlides();
        slides.add(slide);


        assertEquals(2,session.getSlideCount());
    }

    @Test
    void shouldSetId() {
        session.setId(9);
        // ASSERT - Vérification de l'état initial
        assertEquals(9,session.getId());
    }


    @Test
    void shouldRejectSetEmptyName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName(" ")
                // <- lambda function
        );
        assertEquals("Session name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectSetNullName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName(null)
                // <- lambda function
        );
        assertEquals("Session name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectSetShortName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName("ds")
                // <- lambda function
        );
        assertEquals("Session name must be at least 3 characters long", exception.getMessage());
    }


    @Test
    void shouldRejectSetLongName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName("Session name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 charactersSession name cannot exceed 50 characters")
                // <- lambda function
        );
        assertEquals("Session name cannot exceed 50 characters", exception.getMessage());
    }

    @Test
    void shouldRejectSetSpecialCharName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> session.setName("ijfizj!?.:pd")
                // <- lambda function
        );
        assertEquals("Session name contains invalid characters", exception.getMessage());
    }

    @Test
    void shouldSetTitle() {
        session.setName("Session début");
        // ASSERT - Vérification de l'état initial
        assertEquals("Session début",session.getName());
    }


    @Test
    void shouldReturnTrueWhenEmpty() {
        // ASSERT - Vérification de l'état initial
        assertTrue(session.isEmpty());
    }

    @Test
    void shouldReturnFalseWhenNotEmpty() {
        session.addSlide(slide);
        // ASSERT - Vérification de l'état initial
        assertFalse(session.isEmpty());
    }

    @Test
    void shouldClearSlides() {
        session.addSlide(slide);
        session.addSlide(slide);
        session.addSlide(slide);
        session.clear();
        // ASSERT - Vérification de l'état initial
        assertEquals(0,session.getSlideCount());
    }

    @Test
    void shouldReturnTrueIfContainsSlide() {
        session.addSlide(slide);
        // ASSERT - Vérification de l'état initial
        assertTrue(session.contains(slide));
    }

    @Test
    void shouldReturnTrueIfNotContainsSlide() {
        // ASSERT - Vérification de l'état initial
        assertFalse(session.contains(slide));
    }

    @Test
    void shouldReturnIndexOfSlide() {
        session.addSlide(slide);

        // ASSERT - Vérification de l'état initial
        assertEquals(0,session.indexOf(slide));
    }



}