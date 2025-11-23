package fr.bts.iris.slam;

import fr.bts.iris.slam.enchants.CurseEnchantment;
import fr.bts.iris.slam.enchants.FireEnchantment;
import fr.bts.iris.slam.enchants.HolyEnchantment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurseEnchantmentTest  {
    @Test
    void shouldAddDamageToBaseValue() {
        CurseEnchantment curse = new CurseEnchantment(5);

        int result = curse.modifyValue(50);

        assertEquals(45, result);
    }

    @Test
    void shouldAddPrefixToName() {
        CurseEnchantment curse = new CurseEnchantment(5);

        String result = curse.modifyName("Sword");

        assertEquals("Cursed Sword", result);
    }

    @Test
    void shouldChainWithNextEnchantment() {
        CurseEnchantment curse = new CurseEnchantment(5);
        FireEnchantment fire = new FireEnchantment(30);

        curse.setNext(fire);

        int result = curse.modifyValue(50);

        assertEquals(75, result);
    }

    @Test
    void shouldChainNamesWithNextEnchantment() {
        CurseEnchantment cursed = new CurseEnchantment(5);
        FireEnchantment fire = new FireEnchantment(10);

        cursed.setNext(fire);

        String result = cursed.modifyName("Sword"); // "Flaming Cursed Sword"

        assertEquals("Flaming Cursed Sword", result);
    }
}
