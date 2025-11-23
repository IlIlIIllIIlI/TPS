package fr.bts.iris.slam;

import fr.bts.iris.slam.enchants.FireEnchantment;
import fr.bts.iris.slam.enchants.HolyEnchantment;
import fr.bts.iris.slam.strategies.MeleeDamageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MeleeDamageStrategyTest {
    MeleeDamageStrategy damageStrategy;
    Target target;

    @BeforeEach
    void setUp() {
         damageStrategy = new MeleeDamageStrategy(20);
         target = new Target(100);
    }

    @Test
    void ShouldRejectNegativeDamage(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new MeleeDamageStrategy(-80)
                // <- lambda function
        );
        assertEquals("Damage cannot be negative", exception.getMessage());
    }

    @Test
    void ShouldTakeDamage(){
        damageStrategy.attack(target);
        assertEquals(80,target.getCurrentHealth());
    }

    @Test
    void ShouldReturnDamage(){
        assertEquals(20,damageStrategy.getDamage());
    }

    @Test
    void ShouldTakeDamageEnchanted(){
        damageStrategy.attack(target,new HolyEnchantment());
        assertEquals(60,target.getCurrentHealth());
    }

    @Test
    void ShouldReturnDamageEnchanted(){
        assertEquals(40,damageStrategy.getDamage(new HolyEnchantment()));
    }
}
