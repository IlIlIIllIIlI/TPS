package fr.bts.iris.slam;

import fr.bts.iris.slam.capabilities.Enchantable;
import fr.bts.iris.slam.enchants.CurseEnchantment;
import fr.bts.iris.slam.enchants.FireEnchantment;
import fr.bts.iris.slam.enchants.IceEnchantment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnchantableChainTest {
    
    @Test
    void shouldChainMultipleEnchantments() {
        Enchantable fire = new FireEnchantment(10);
        Enchantable ice = new IceEnchantment();
        Enchantable curse = new CurseEnchantment(5);
        
        fire.setNext(ice);
        ice.setNext(curse);
        
        int result = fire.modifyValue(50); // (50+10)×1.5−5 = 85
        
        assertEquals(85, result);
    }

    @Test
    void shouldChainMultipleEnchantmentsName() {
        Enchantable fire = new FireEnchantment(10);
        Enchantable ice = new IceEnchantment();
        Enchantable curse = new CurseEnchantment(5);

        fire.setNext(ice);
        ice.setNext(curse);

        String result = fire.modifyName("Sword"); // Cursed Frozen Flaming Sword

        assertEquals("Cursed Frozen Flaming Sword", result);
    }}