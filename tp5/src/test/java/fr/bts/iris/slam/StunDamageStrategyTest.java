package fr.bts.iris.slam;

import fr.bts.iris.slam.enchants.HolyEnchantment;
import fr.bts.iris.slam.strategies.MeleeDamageStrategy;
import fr.bts.iris.slam.strategies.StunDamageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StunDamageStrategyTest {
    StunDamageStrategy stunDamageStrategy;
    Target target;

    @BeforeEach
    void setUp() {
        stunDamageStrategy = new StunDamageStrategy(20,10);
        target = new Target(100);
    }

    @Test
    void ShouldRejectNegativeDamage(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new StunDamageStrategy(-80,10)
                // <- lambda function
        );
        assertEquals("Damage cannot be negative", exception.getMessage());
    }

    @Test
    void ShouldRejectNegativeStunDuration(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new StunDamageStrategy(70,-10)
                // <- lambda function
        );
        assertEquals("Stun duration cannot be negative", exception.getMessage());
    }

    @Test
    void ShouldTakeDamage(){
        stunDamageStrategy.attack(target);
        assertEquals(80,target.getCurrentHealth());
    }

    @Test
    void ShouldApplyStun(){
        stunDamageStrategy.attack(target);
        assertTrue(target.isStunned());
        assertEquals(10,target.getStunDuration());
    }


    @Test
    void ShouldReturnDamage(){
        assertEquals(20, stunDamageStrategy.getDamage());
    }
    @Test
    void ShouldReturnStunDuration(){
        assertEquals(10, stunDamageStrategy.getStunDuration());
    }

    @Test
    void ShouldTakeDamageEnchanted(){
        stunDamageStrategy.attack(target,new HolyEnchantment());
        assertEquals(60,target.getCurrentHealth());
        assertTrue(target.isStunned());
        assertEquals(10,target.getStunDuration());
    }

    @Test
    void ShouldReturnDamageEnchanted(){
        assertEquals(40,stunDamageStrategy.getDamage(new HolyEnchantment()));
    }
}
