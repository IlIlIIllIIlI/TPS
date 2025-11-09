package fr.bts.iris.slam.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SlideTest {
    private Slide slide;
    private final String TITLE = "Bonjour";
    private final String TITLE_COLOR = "#AAAAAA";
    private final String PATH = "yey.gif";
    private final int POSITION = 3;


    @BeforeEach
    void setUp() {
        slide = new Slide(TITLE, TITLE_COLOR, PATH,POSITION);
    }

    // === TESTS DE CONSTRUCTION ===

    @Test
    void shouldCreateSlideWithValidParameters() {
        Slide newSlide = new Slide("Aurevoir", "#BAAAAA",  "yay.gIf",5);

        // ASSERT - Vérification de l'état initial
        assertEquals("Aurevoir", newSlide.getTitle());
        assertEquals("#BAAAAA", newSlide.getTitleColor());
        assertEquals(5, newSlide.getPosition());
        assertEquals("yay.gIf", newSlide.getImagePath());
        assertEquals(0, newSlide.getId());
    }

    @Test
    void shouldCreateSlideWithIdWithValidParameters() {
        Slide newSlide = new Slide(1,"Aurevoir", "#BAAAAA",  "yay.gif",5);

        // ASSERT - Vérification de l'état initial
        assertEquals("Aurevoir", newSlide.getTitle());
        assertEquals("#BAAAAA", newSlide.getTitleColor());
        assertEquals(5, newSlide.getPosition());
        assertEquals("yay.gif", newSlide.getImagePath());
        assertEquals(1, newSlide.getId());
    }

    @Test
    void shouldRejectNullTitle() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(null, "#BAAAAA", "yipee.gif",5)
                // <- lambda function
        );
        assertEquals("Title cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectSetEmptyTitle() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setTitle(" ")
                // <- lambda function
        );
        assertEquals("Title cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectSetShortTitle() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setTitle("yo")
                // <- lambda function
        );
        assertEquals("Title must be at least 3 characters long", exception.getMessage());
    }

    @Test
    void shouldRejectSetLongTitle() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setTitle(" YfzFFZFZFZ?OojfozjOFJozjfIPHGphzHZIPHFhfpiZBFUzfPIHFpzihfgfIPZFGzpifgIFGPIzfpigPZIFisfbsPBFZPifbSFBpzibsfpIBFZibfipbFZIbpbibpiifZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZo   ")
                // <- lambda function
        );
        assertEquals("Title cannot exceed 100 characters", exception.getMessage());
    }

    @Test
    void shouldRejectSetNullTitleColor() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setTitleColor(null)
                // <- lambda function
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectSetNotSevenCharacterTitleColor() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setTitleColor("#FF5454F")
                // <- lambda function
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectSetNotStartingWithSharpCharacterTitleColor() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setTitleColor("945451")
                // <- lambda function
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectSetInvalidCharacterTitleColor() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setTitleColor("#GD5487")
                // <- lambda function
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectSetNullImagePath() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setImagePath(null)
                // <- lambda function
        );
        assertEquals("Image path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectSetEmptyImagePath() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setImagePath("  ")
                // <- lambda function
        );
        assertEquals("Image path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectSetShortImagePath() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setImagePath("e.gf")
                // <- lambda function
        );
        assertEquals("Image path is too short", exception.getMessage());
    }

    @Test
    void shouldRejectSetWrongExtensionImagePath() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setImagePath("zfds.doc")
                // <- lambda function
        );
        assertEquals("Image path must end with .jpg, .jpeg, .png, or .gif", exception.getMessage());
    }

    @Test
    void shouldRejectSetPositionBelow1(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> slide.setPosition(-3)
                // <- lambda function
        );
        assertEquals("Position can't be below 1", exception.getMessage());
    }

    @Test
    void shouldDisplay() {

        // ASSERT - Vérification de l'état initial
        assertEquals("Slide[title= Bonjour, color= #AAAAAA, image= yey.gif, position= 3]",slide.display());
    }


    @Test
    void shouldSetId() {
        slide.setId(9);
        // ASSERT - Vérification de l'état initial
        assertEquals(9,slide.getId());
    }

    @Test
    void shouldSetTitle() {
        slide.setTitle("Intro");
        // ASSERT - Vérification de l'état initial
        assertEquals("Intro",slide.getTitle());
    }

    @Test
    void shouldSetTitleColor() {
        slide.setTitleColor("#986532");
        // ASSERT - Vérification de l'état initial
        assertEquals("#986532",slide.getTitleColor());
    }

    @Test
    void shouldSetImagePath() {
        slide.setImagePath("koqfk.png");
        // ASSERT - Vérification de l'état initial
        assertEquals("koqfk.png",slide.getImagePath());
    }


    @Test
    void shouldSetPosition() {
        slide.setPosition(7);
        // ASSERT - Vérification de l'état initial
        assertEquals(7,slide.getPosition());
    }


    @Test
    void shouldRejectEmptyTitle() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" ", "#BAAAAA", "yipee.gif",5)
                // <- lambda function
        );
        assertEquals("Title cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectShortTitle() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Yo   ", "#BAAAAA", "yipee.gif",5)
                // <- lambda function
        );
        assertEquals("Title must be at least 3 characters long", exception.getMessage());
    }

    @Test
    void shouldRejectLongTitle() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" YfzFFZFZFZ?OojfozjOFJozjfIPHGphzHZIPHFhfpiZBFUzfPIHFpzihfgfIPZFGzpifgIFGPIzfpigPZIFisfbsPBFZPifbSFBpzibsfpIBFZibfipbFZIbpbibpiifZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZo   ", "#BAAAAA", "yipee.gif",5)
                // <- lambda function
        );
        assertEquals("Title cannot exceed 100 characters", exception.getMessage());
    }

    @Test
    void shouldRejectNullTitleColor() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Compile   ", null, "yipee.gif",5)
                // <- lambda function
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectNotSevenCharacterTitleColor() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Compile   ", "#FF0010252", "yipee.gif",5)
                // <- lambda function
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectNotStartingWithSharpCharacterTitleColor() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Compile   ", "FF0000", "yipee.gif",5)
                // <- lambda function
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectInvalidCharacterTitleColor() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Compile   ", "#GGHHII", "yipee.gif",5)
                // <- lambda function
        );
        assertEquals("Invalid color format. Expected #RRGGBB (e.g., #FF0000)", exception.getMessage());
    }

    @Test
    void shouldRejectNullImagePath() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Compile   ", "#FFAABB", null,5)
                // <- lambda function
        );
        assertEquals("Image path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectEmptyImagePath() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Compile   ", "#FFAABB", "   ",5)
                // <- lambda function
        );
        assertEquals("Image path cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldRejectShortImagePath() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Compile   ", "#FFAABB", "e.gf",5)
                // <- lambda function
        );
        assertEquals("Image path is too short", exception.getMessage());
    }

    @Test
    void shouldRejectWrongExtensionImagePath() {
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Compile   ", "#FFAABB", "hrhrr.doc",5)
                // <- lambda function
        );
        assertEquals("Image path must end with .jpg, .jpeg, .png, or .gif", exception.getMessage());
    }

    @Test
    void shouldRejectPositionBelow1(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Slide(" Compile   ", "#FFAABB", "hrhrr.gif",-3)
                // <- lambda function
        );
        assertEquals("Position can't be below 1", exception.getMessage());
    }

}
