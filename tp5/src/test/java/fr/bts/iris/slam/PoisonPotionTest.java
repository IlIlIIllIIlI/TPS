package fr.bts.iris.slam;

import fr.bts.iris.slam.items.PoisonPotion;
import fr.bts.iris.slam.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PoisonPotionTest {
    private PoisonPotion potion;
    private Target enemy;

    @BeforeEach
    void setUp() {
        potion = new PoisonPotion("Poison Potion", 50);
        enemy = new Target(100);
    }

    @Test
    void shouldAttackTarget() {
        potion.attack(enemy);
        assertEquals(50, enemy.getCurrentHealth());
    }

    @Test
    void shouldReturnCorrectDamage() {
        assertEquals(50, potion.getDamage());
    }

    @Test
    void shouldHaveCorrectName() {
        assertEquals("Poison Potion", potion.getName());
    }
}
