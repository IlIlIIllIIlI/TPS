package fr.bts.iris.slam.strategies;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Damaging;
import fr.bts.iris.slam.capabilities.Enchantable;

public class MagicDamageStrategy implements EnchantableDamaging {
    protected int damage;

    public MagicDamageStrategy(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        this.damage=damage;
    }

    public void attack(Target target){
        target.takeDamage(this.damage);
    }

    public int getDamage() {
        return this.damage;
    }
    @Override
    public void attack(Target target, Enchantable enchantments) {
        if (enchantments == null) {
            this.attack(target);
            return;
        }
        target.takeDamage(enchantments.modifyValue(this.damage));

    }

    @Override
    public int getDamage(Enchantable enchantments) {
        if (enchantments == null) {
            return this.getDamage();
        }
        return enchantments.modifyValue(this.damage);
    }
}
