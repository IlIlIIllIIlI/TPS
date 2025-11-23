package fr.bts.iris.slam;

import fr.bts.iris.slam.enchants.FireEnchantment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FireEnchantmentTest {
    
    @Test
    void shouldRejectNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            new FireEnchantment(-5);
        });
    }

    @Test
    void shouldAddDamageToBaseValue() {
        FireEnchantment fire = new FireEnchantment(10);
        
        int result = fire.modifyValue(50);
        
        assertEquals(60, result);
    }
    
    @Test
    void shouldAddPrefixToName() {
        FireEnchantment fire = new FireEnchantment(10);
        
        String result = fire.modifyName("Sword");
        
        assertEquals("Flaming Sword", result);
    }
    
    @Test
    void shouldChainWithNextEnchantment() {
        FireEnchantment fire = new FireEnchantment(10);
        FireEnchantment fire2 = new FireEnchantment(30);
        
        fire.setNext(fire2);
        
        int result = fire.modifyValue(50); // (50+10+30) = 90
        
        assertEquals(90, result);
    }
    
    @Test
    void shouldChainNamesWithNextEnchantment() {
        FireEnchantment fire = new FireEnchantment(10);
        FireEnchantment fire2 = new FireEnchantment(10);
        
        fire.setNext(fire2);
        
        String result = fire.modifyName("Sword"); // "Flaming Flaming Sword"
        
        assertEquals("Flaming Flaming Sword", result);
    }
}