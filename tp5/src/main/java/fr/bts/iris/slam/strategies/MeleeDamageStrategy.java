package fr.bts.iris.slam.strategies;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Damaging;
import fr.bts.iris.slam.capabilities.Enchantable;

public class MeleeDamageStrategy implements EnchantableDamaging {
    protected int damage;

    public MeleeDamageStrategy(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        this.damage=damage;
    }
    @Override
    public void attack(Target target){
        target.takeDamage(this.damage);
    }
    @Override
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
