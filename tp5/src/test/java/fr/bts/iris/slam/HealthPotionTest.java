package fr.bts.iris.slam;

import fr.bts.iris.slam.items.HealthPotion;
import fr.bts.iris.slam.items.Sword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthPotionTest {
    private HealthPotion potion;
    private Target ally;

    @BeforeEach
    void setUp() {
        potion = new HealthPotion("Health Potion", 50);
        ally = new Target(100);
    }

    @Test
    void shouldHealTarget() {
        ally.takeDamage(50);

        potion.heal(ally);
        assertEquals(100, ally.getCurrentHealth());
    }

    @Test
    void shouldReturnCorrectHealing() {
        assertEquals(50, potion.getHealPower());
    }

    @Test
    void shouldHaveCorrectName() {
        assertEquals("Health Potion", potion.getName());
    }


}
