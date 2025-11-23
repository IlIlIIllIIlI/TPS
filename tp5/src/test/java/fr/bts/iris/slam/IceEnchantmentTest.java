package fr.bts.iris.slam;

import fr.bts.iris.slam.enchants.FireEnchantment;
import fr.bts.iris.slam.enchants.IceEnchantment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IceEnchantmentTest {


    @Test
    void shouldAddDamageToBaseValue() {
        IceEnchantment ice = new IceEnchantment();

        int result = ice.modifyValue(50);

        assertEquals(75, result);
    }

    @Test
    void shouldAddPrefixToName() {
        IceEnchantment ice = new IceEnchantment();

        String result = ice.modifyName("Sword");

        assertEquals("Frozen Sword", result);
    }

    @Test
    void shouldChainWithNextEnchantment() {
        IceEnchantment ice = new IceEnchantment();
        FireEnchantment fire = new FireEnchantment(30);

        ice.setNext(fire);

        int result = ice.modifyValue(50);

        assertEquals(105, result);
    }

    @Test
    void shouldChainNamesWithNextEnchantment() {
        IceEnchantment ice = new IceEnchantment();
        FireEnchantment fire = new FireEnchantment(10);

        ice.setNext(fire);

        String result = ice.modifyName("Sword"); // "Flaming Frozen Sword"

        assertEquals("Flaming Frozen Sword", result);
    }
}
