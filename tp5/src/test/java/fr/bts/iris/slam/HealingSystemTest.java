package fr.bts.iris.slam;

import fr.bts.iris.slam.systems.DamageSystem;
import fr.bts.iris.slam.systems.HealingSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HealingSystemTest {
    HealingSystem hsys;
    Target target;

    @BeforeEach
    void setUp() {
         hsys = new HealingSystem(20);
         target = new Target(100);
        target.takeDamage(20);
    }

    @Test
    void ShouldRejectNegativeDamage(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new HealingSystem(-80)
                // <- lambda function
        );
        assertEquals("Heal amount cannot be negative", exception.getMessage());
    }

    @Test
    void ShouldHeal(){
        hsys.heal(target);
        assertEquals(100,target.getCurrentHealth());
    }

    @Test
    void ShouldReturnHealing(){
        assertEquals(20,hsys.getHealing());
    }
}
