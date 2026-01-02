package fr.bts.iris.slam.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FlashCardTest {
    private FlashCard card;
    private final String QUESTION = "Bonjour";
    private final String ANSWER = "AAAAAA";


    @BeforeEach
    void setUp() {
        card = new FlashCard(QUESTION, ANSWER);
    }


    // === TESTS DE CONSTRUCTION ===

    @Test
    void shouldCreateFlashCardWithValidParameters() {
        FlashCard newCard = new FlashCard("Bonjour", "BAAAAA");

        // ASSERT - Vérification de l'état initial
        assertEquals("Bonjour", newCard.getQuestion());
        assertEquals("BAAAAA", newCard.getAnswer());
    }

    @Test
    void shouldCreateFlashCardWithIdWithValidParameters() {
        FlashCard newCard = new FlashCard(1,"Aurevoir", "BAAAAA");

        // ASSERT - Vérification de l'état initial
        assertEquals("Aurevoir", newCard.getQuestion());
        assertEquals("BAAAAA", newCard.getAnswer());
        assertEquals(1, newCard.getId());
    }


    @Test
    void shouldRejectNullQuestion() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new FlashCard(null, "#BAAAAA")
                // <- lambda function
        );
        assertEquals("Question cannot be empty or null", exception.getMessage());
    }

    @Test
    void shouldRejectSetEmptyQuestion() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> card.setQuestion(" ")
                // <- lambda function
        );
        assertEquals("Question cannot be empty or null", exception.getMessage());
    }


    @Test
    void shouldRejectNullAnswer() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new FlashCard("yipee", null)
                // <- lambda function
        );
        assertEquals("Answer cannot be empty or null", exception.getMessage());
    }

    @Test
    void shouldRejectSetEmptyTitle() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> card.setAnswer(" ")
                // <- lambda function
        );
        assertEquals("Answer cannot be empty or null", exception.getMessage());
    }

    @Test
    void shouldSetId() {
        card.setId(9);
        // ASSERT - Vérification de l'état initial
        assertEquals(9, card.getId());
    }

    @Test
    void shouldSetQuestion() {
        card.setQuestion("Intro");
        // ASSERT - Vérification de l'état initial
        assertEquals("Intro", card.getQuestion());
    }

    @Test
    void shouldSetDescription() {
        card.setAnswer("yipee");
        // ASSERT - Vérification de l'état initial
        assertEquals("yipee", card.getAnswer());
    }

    @Test
    void shouldGetId() {
        // ASSERT - Vérification de l'état initial
        assertEquals(0, card.getId());
    }

    @Test
    void shouldGetQuestion() {
        // ASSERT - Vérification de l'état initial
        assertEquals("Bonjour", card.getQuestion());
    }

    @Test
    void shouldGetDescription() {
        // ASSERT - Vérification de l'état initial
        assertEquals("AAAAAA", card.getAnswer());
    }
}
