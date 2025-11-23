package fr.bts.iris.slam.strategies;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Damaging;
import fr.bts.iris.slam.capabilities.Enchantable;

public interface EnchantableDamaging extends Damaging {
    void attack(Target target, Enchantable enchantments); // Surcharge
    int getDamage(Enchantable enchantments); // Surcharge
}

