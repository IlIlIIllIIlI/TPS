package fr.bts.iris.slam;

import fr.bts.iris.slam.items.StunningMace;
import fr.bts.iris.slam.items.Sword;
import fr.bts.iris.slam.strategies.StunDamageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StunningMaceTest {

    private StunningMace mace;
    private Target enemy;

    @BeforeEach
    void setUp() {
        mace = new StunningMace("Mace", new StunDamageStrategy(50,10));
        enemy = new Target(100);
    }

    @Test
    void shouldAttackAndStunTarget() {
        mace.attack(enemy);
        assertEquals(50, enemy.getCurrentHealth());
        assertTrue(enemy.isStunned());
        assertEquals(10, enemy.getStunDuration());

    }

    @Test
    void shouldReturnCorrectDamage() {
        assertEquals(50, mace.getDamage());
    }

    @Test
    void shouldHaveCorrectName() {
        assertEquals("Mace", mace.getName());
    }

    @Test
    void ShouldRejectNullDamageStrategy(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new StunningMace("mace",null)
                // <- lambda function
        );
        assertEquals("Damage strategy can't be null", exception.getMessage());
    }
}
