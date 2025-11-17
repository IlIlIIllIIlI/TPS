package fr.bts.iris.slam;

import fr.bts.iris.slam.items.DruidStaff;
import fr.bts.iris.slam.items.HealingMace;
import fr.bts.iris.slam.strategies.BasicHealingStrategy;
import fr.bts.iris.slam.strategies.MagicDamageStrategy;
import fr.bts.iris.slam.strategies.RegenerationStrategy;
import fr.bts.iris.slam.strategies.StunDamageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealingMaceTest {
    private HealingMace mace;
    private Target target;

    @BeforeEach
    void setUp() {
        mace = new HealingMace("Druid Mace", new StunDamageStrategy(50,10),new RegenerationStrategy(50));
        target = new Target(100);
    }

    @Test
    void shouldHealTarget() {
        target.takeDamage(50);

        mace.heal(target);
        assertEquals(100, target.getCurrentHealth());
    }

    @Test
    void shouldReturnCorrectHealing() {
        assertEquals(50, mace.getHealPower());
    }

    @Test
    void shouldHaveCorrectName() {
        assertEquals("Druid Mace", mace.getName());
    }


    @Test
    void shouldAttackAndStunTarget() {
        mace.attack(target);
        assertEquals(50, target.getCurrentHealth());
        assertTrue(target.isStunned());
        assertEquals(10, target.getStunDuration());
    }

    @Test
    void shouldReturnCorrectDamage() {
        assertEquals(50, mace.getDamage());
    }

    @Test
    void ShouldRejectNullDamageStrategy(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DruidStaff("Druid Mace",null, new BasicHealingStrategy(50))
                // <- lambda function
        );
        assertEquals("Damage strategy can't be null", exception.getMessage());
    }

    @Test
    void ShouldRejectNullHealingStrategy(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DruidStaff("Druid Mace",new MagicDamageStrategy(50), null)
                // <- lambda function
        );
        assertEquals("Healing strategy can't be null", exception.getMessage());
    }

}
