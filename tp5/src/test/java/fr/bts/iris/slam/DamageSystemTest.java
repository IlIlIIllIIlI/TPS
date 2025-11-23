package fr.bts.iris.slam;

import fr.bts.iris.slam.systems.DamageSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DamageSystemTest {
    DamageSystem dsys;
    Target target;
    @BeforeEach
    void setUp() {
         dsys = new DamageSystem(20);
         target = new Target(100);
    }

    @Test
    void ShouldRejectNegativeDamage(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DamageSystem(-80)
                // <- lambda function
        );
        assertEquals("Damage cannot be negative", exception.getMessage());
    }

    @Test
    void ShouldTakeDamage(){
        dsys.hit(target);
        assertEquals(80,target.getCurrentHealth());
    }

    @Test
    void ShouldReturnDamage(){
        assertEquals(20,dsys.getDamage());
    }
}
