package fr.bts.iris.slam;

import fr.bts.iris.slam.enchants.FireEnchantment;
import fr.bts.iris.slam.enchants.HolyEnchantment;
import fr.bts.iris.slam.enchants.IceEnchantment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HolyEnchantmentTest {
    @Test
    void shouldAddDamageToBaseValue() {
        HolyEnchantment holy = new HolyEnchantment();

        int result = holy.modifyValue(50);

        assertEquals(70, result);
    }

    @Test
    void shouldAddPrefixToName() {
        HolyEnchantment holy = new HolyEnchantment();

        String result = holy.modifyName("Sword");

        assertEquals("Holy Sword", result);
    }

    @Test
    void shouldChainWithNextEnchantment() {
        HolyEnchantment holy = new HolyEnchantment();
        FireEnchantment fire = new FireEnchantment(30);

        holy.setNext(fire);

        int result = holy.modifyValue(50);

        assertEquals(100, result);
    }

    @Test
    void shouldChainNamesWithNextEnchantment() {
        HolyEnchantment holy = new HolyEnchantment();
        FireEnchantment fire = new FireEnchantment(10);

        holy.setNext(fire);

        String result = holy.modifyName("Sword"); // "Flaming Holy Sword"

        assertEquals("Flaming Holy Sword", result);
    }
}
