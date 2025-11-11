package fr.bts.iris.slam;

import fr.bts.iris.slam.enchants.HolyEnchantment;
import fr.bts.iris.slam.strategies.CriticalDamageStrategy;
import fr.bts.iris.slam.strategies.MeleeDamageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CriticalDamageStrategyTest {
    CriticalDamageStrategy criticalDamageStrategy;
    Target target;

    @BeforeEach
    void setUp() {
        criticalDamageStrategy = new CriticalDamageStrategy(20,0);
        target = new Target(100);
    }

    @Test
    void ShouldRejectNegativeDamage(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CriticalDamageStrategy(-80,0.3)
                // <- lambda function
        );
        assertEquals("Damage cannot be negative", exception.getMessage());
    }

    @Test
    void ShouldRejectNegativeCriticalChance(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CriticalDamageStrategy(80,-0.5)
                // <- lambda function
        );
        assertEquals("Critical duration cannot be negative or above 1 ", exception.getMessage());
    }

    @Test
    void ShouldRejectAboveOneCriticalChance(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CriticalDamageStrategy(80,1.5)
                // <- lambda function
        );
        assertEquals("Critical duration cannot be negative or above 1 ", exception.getMessage());
    }

    @Test
    void ShouldTakeDamage(){
        criticalDamageStrategy.attack(target);
        assertEquals(80,target.getCurrentHealth());
    }

    @Test
    void ShouldTakeCriticalDamage(){
        criticalDamageStrategy = new CriticalDamageStrategy(20,1);
        criticalDamageStrategy.attack(target);
        assertEquals(60,target.getCurrentHealth());
    }

    @Test
    void ShouldReturnDamage(){
        assertEquals(20, criticalDamageStrategy.getDamage());
    }
    @Test
    void ShouldReturnCriticalChance(){
        assertEquals(0, criticalDamageStrategy.getCritChance());
    }

    @Test
    void ShouldTakeDamageEnchanted(){
        criticalDamageStrategy.attack(target,new HolyEnchantment());
        assertEquals(60,target.getCurrentHealth());
    }

    @Test
    void ShouldTakeCriticalDamageEnchanted(){
        criticalDamageStrategy = new CriticalDamageStrategy(20,1);
        criticalDamageStrategy.attack(target,new HolyEnchantment());
        assertEquals(20,target.getCurrentHealth());
    }

    @Test
    void ShouldReturnDamageEnchanted(){
        assertEquals(40,criticalDamageStrategy.getDamage(new HolyEnchantment()));
    }
}
