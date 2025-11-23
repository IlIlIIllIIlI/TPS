package fr.bts.iris.slam;

import fr.bts.iris.slam.items.Sword;
import fr.bts.iris.slam.strategies.MagicDamageStrategy;
import fr.bts.iris.slam.strategies.MeleeDamageStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class SwordTest {
    
    private Sword sword;
    private Target enemy;
    
    @BeforeEach
    void setUp() {
        // Partie 1 : Sword crée son CombatSystem en interne
        sword = new Sword("Iron Blade", new MeleeDamageStrategy(50));
        enemy = new Target(100);
    }
    
    @Test
    void shouldAttackTarget() {
        sword.attack(enemy);
        assertEquals(50, enemy.getCurrentHealth());
    }
    
    @Test
    void shouldReturnCorrectDamage() {
        assertEquals(50, sword.getDamage());
    }
    
    @Test
    void shouldHaveCorrectName() {
        assertEquals("Iron Blade", sword.getName());
    }

    @Test
    void ShouldRejectNullDamageStrategy(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Sword("sword",null)
                // <- lambda function
        );
        assertEquals("Damage strategy can't be null", exception.getMessage());
    }

    @Test
    void shouldHandleStrategySwitch() {
        sword.attack(enemy);
        sword =  new Sword("Magic Iron Blade", new MagicDamageStrategy(10));
        sword.attack(enemy);
        assertEquals(40, enemy.getCurrentHealth());
    }


}