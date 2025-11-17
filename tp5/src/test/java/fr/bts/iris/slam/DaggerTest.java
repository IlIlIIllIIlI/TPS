package fr.bts.iris.slam;

import fr.bts.iris.slam.items.Dagger;
import fr.bts.iris.slam.items.Sword;
import fr.bts.iris.slam.strategies.CriticalDamageStrategy;
import fr.bts.iris.slam.strategies.MeleeDamageStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DaggerTest {

    private Dagger dagger;
    private Target enemy;

    @BeforeEach
    void setUp() {
        dagger = new Dagger("Iron Dagger", new CriticalDamageStrategy(50,0));
        enemy = new Target(100);
    }

    @Test
    void shouldAttackTarget() {
        dagger.attack(enemy);
        assertEquals(50, enemy.getCurrentHealth());
    }

    @Test
    void ShouldTakeCriticalDamage(){
        dagger = new Dagger("Iron Dagger",new CriticalDamageStrategy(20,1));
        dagger.attack(enemy);
        assertEquals(60,enemy.getCurrentHealth());
    }

    @Test
    void shouldReturnCorrectDamage() {
        assertEquals(50, dagger.getDamage());
    }

    @Test
    void shouldHaveCorrectName() {
        assertEquals("Iron Dagger", dagger.getName());
    }

    @Test
    void ShouldRejectNullDamageStrategy(){
        // Vérifier qu'une IllegalArgumentException est levée
        // avec un message approprié


        // ACT & ASSERT : l'action et la vérification en une fois
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Dagger("Iron Dagger",null)
                // <- lambda function
        );
        assertEquals("Damage strategy can't be null", exception.getMessage());
    }
}
