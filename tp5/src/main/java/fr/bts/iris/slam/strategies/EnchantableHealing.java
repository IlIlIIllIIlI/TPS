package fr.bts.iris.slam.strategies;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Enchantable;
import fr.bts.iris.slam.capabilities.Healing;

public interface EnchantableHealing extends Healing {
    void heal(Target target, Enchantable enchantments); // Surcharge
    int getHealPower(Enchantable enchantments); // Surcharge
}
