package fr.bts.iris.slam;

import fr.bts.iris.slam.enchants.HolyEnchantment;
import fr.bts.iris.slam.strategies.BasicHealingStrategy;
import fr.bts.iris.slam.systems.HealingSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicHealingStrategyTest {
    BasicHealingStrategy healingStrategy;
    Target target;

    @BeforeEach
    void setUp() {
        healingStrategy = new BasicHealingStrategy(20);
        target = new Target(100);
        target.takeDamage(40);
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
        healingStrategy.heal(target);
        assertEquals(80,target.getCurrentHealth());
    }

    @Test
    void ShouldReturnHealing(){
        assertEquals(20, healingStrategy.getHealPower());
    }

    @Test
    void ShouldTakeDamageEnchanted(){
        healingStrategy.heal(target,new HolyEnchantment());
        assertEquals(100,target.getCurrentHealth());
    }

    @Test
    void ShouldReturnDamageEnchanted(){
        assertEquals(40,healingStrategy.getHealPower(new HolyEnchantment()));
    }
}
