package fr.bts.iris.slam.enchants;

import fr.bts.iris.slam.capabilities.Enchantable;

public class EnchantmentChainBuilder {
    private Enchantable first;
    private Enchantable last;
    
    public EnchantmentChainBuilder add(Enchantable enchantment) {
        if (enchantment == null) {
            throw new IllegalArgumentException("Enchantment cannot be null");
        }
        
        if (first == null) {
            // Premier enchantement de la chaîne
            first = enchantment;
            last = enchantment;
        } else {
            // Ajouter à la fin de la chaîne
            last.setNext(enchantment);
            last = enchantment;
        }
        
        return this; // Fluent interface
    }
    
    public Enchantable build() {
        return first;
    }
}