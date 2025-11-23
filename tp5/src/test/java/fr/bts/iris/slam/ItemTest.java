import fr.bts.iris.slam.items.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    
    // // Item est abstraite, donc on crée une classe concrète minimale pour la tester
    private static class TestItem extends Item {
        public TestItem(String name) {
            super(name);
        }
    }
    
    private Item item;
    
    @BeforeEach
    void setUp() {
        item = new TestItem("Test Item");
    }
    
    @Test
    void shouldCreateItemWithValidName() {
        assertEquals("Test Item", item.getName());
    }
    
    @Test
    void shouldRejectNullName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestItem(null);
        });
    }
    
    @Test
    void shouldRejectEmptyName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestItem("   ");
        });
    }
}