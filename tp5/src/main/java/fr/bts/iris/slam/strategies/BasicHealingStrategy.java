package fr.bts.iris.slam.strategies;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Enchantable;
import fr.bts.iris.slam.capabilities.Healing;

public class BasicHealingStrategy implements EnchantableHealing {
    protected int healing;
    public BasicHealingStrategy(int healing) {
        if (healing < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }

        this.healing = healing;
    }
    @Override
    public void heal(Target target){
        target.heal(this.healing);
    }
    @Override
    public int getHealPower() {
        return this.healing;
    }

    @Override
    public void heal(Target target, Enchantable enchantments) {
        if (enchantments == null) {
            this.heal(target);
            return;
        }
        target.heal(enchantments.modifyValue(this.healing));

    }

    @Override
    public int getHealPower(Enchantable enchantments) {
        if (enchantments == null) {
            return this.getHealPower();
        }
        return enchantments.modifyValue(this.healing);
    }
}
