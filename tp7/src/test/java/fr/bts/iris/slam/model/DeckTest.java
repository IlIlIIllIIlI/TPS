package fr.bts.iris.slam.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    private Deck deck;
    private FlashCard card;
    private final String NAME = "Bonjour";
    private final String DESCRITPTION = "AAAAAA";


    @BeforeEach
    void setUp() {
        deck = new Deck(NAME, DESCRITPTION);
        card = new FlashCard(NAME, DESCRITPTION);
    }

    @Test
    void shouldCreateDeckWithValidParameters() {
        Deck newDeck = new Deck("Bonjour", "BAAAAA");

        // ASSERT - Vérification de l'état initial
        assertEquals("Bonjour", newDeck.getName());
        assertEquals("BAAAAA", newDeck.getDescription());
    }

    @Test
    void shouldCreateDeckWithIdWithValidParameters() {
        Deck newDeck = new Deck(1,"Aurevoir", "BAAAAA");

        // ASSERT - Vérification de l'état initial
        assertEquals("Aurevoir", newDeck.getName());
        assertEquals("BAAAAA", newDeck.getDescription());
        assertEquals(1, newDeck.getId());
    }

    @Test
    void shouldRejectEmptyName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Deck(" ","yipee")
                // <- lambda function
        );
        assertEquals("Name cannot be empty or null", exception.getMessage());
    }

    @Test
    void shouldRejectNullName() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Deck(null,"yipee")
                // <- lambda function
        );
        assertEquals("Name cannot be empty or null", exception.getMessage());
    }

    @Test
    void shouldRejectEmptyDescription() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Deck("yipee"," ")
                // <- lambda function
        );
        assertEquals("Description cannot be empty or null", exception.getMessage());
    }

    @Test
    void shouldRejectNullDescription() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Deck("yipee",null)
                // <- lambda function
        );
        assertEquals("Description cannot be empty or null", exception.getMessage());
    }


    @Test
    void shouldRejectNullAddCard() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> deck.addCard(null)
                // <- lambda function
        );
        assertEquals("FlashCard cannot be null", exception.getMessage());
    }

    @Test
    void shouldAddCard() {

        deck.addCard(card);
        assertEquals(card.getQuestion(),deck.getCard(0).getQuestion());
    }

    @Test
    void shouldRejectNullRemoveCard() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> deck.removeCard(null)
                // <- lambda function
        );
        assertEquals("FlashCard cannot be null", exception.getMessage());
    }

    @Test
    void shouldRemoveCard() {
        deck.addCard(card);
        deck.removeCard(card);

        IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class,
                () -> deck.getCard(0)
        );
    }
}
