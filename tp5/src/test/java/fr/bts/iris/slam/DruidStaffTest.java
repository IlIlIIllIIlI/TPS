package fr.bts.iris.slam;

import fr.bts.iris.slam.items.DruidStaff;
import fr.bts.iris.slam.items.HealthPotion;
import fr.bts.iris.slam.items.Sword;
import fr.bts.iris.slam.strategies.BasicHealingStrategy;
import fr.bts.iris.slam.strategies.MagicDamageStrategy;
import fr.bts.iris.slam.strategies.MeleeDamageStrategy;
import fr.bts.iris.slam.strategies.RegenerationStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DruidStaffTest {
    private DruidStaff staff;
    private Target target;

    @BeforeEach
    void setUp() {
        staff = new DruidStaff("Druid Staff", new MagicDamageStrategy(50),new RegenerationStrategy(50));
        target = new Target(100);
    }

    @Test
    void shouldHealTarget() {
        target.takeDamage(50);

        staff.heal(target);
        assertEquals(100, target.getCurrentHealth());
    }

    @Test
    void shouldReturnCorrectHealing() {
        assertEquals(50, staff.getHealPower());
    }

    @Test
    void shouldHaveCorrectName() {
        assertEquals("Druid Staff", staff.getName());
    }


    @Test
    void shouldAttackTarget() {
        staff.attack(target);
        assertEquals(50, target.getCurrentHealth());
    }

    @Test
    void shouldReturnCorrectDamage() {
        assertEquals(50, staff.getDamage());
    }

    @Test
    void ShouldRejectNullDamageStrategy(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new DruidStaff("staff",null, new BasicHealingStrategy(50))
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
                () -> new DruidStaff("staff",new MagicDamageStrategy(50), null)
                // <- lambda function
        );
        assertEquals("Healing strategy can't be null", exception.getMessage());
    }

    @Test
    void shouldHandleStrategySwitch() {
        staff.attack(target);
        staff =  new DruidStaff("Melee Druid Staff", new MeleeDamageStrategy(10), new BasicHealingStrategy(20));
        staff.attack(target);
        assertEquals(40, target.getCurrentHealth());
    }




}
