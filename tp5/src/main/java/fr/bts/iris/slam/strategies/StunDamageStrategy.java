package fr.bts.iris.slam.strategies;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Damaging;
import fr.bts.iris.slam.capabilities.Enchantable;

public class StunDamageStrategy implements EnchantableDamaging {
    protected int damage;
    protected double stunDuration;

    public StunDamageStrategy(int damage,double stunDuration ) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        if (stunDuration < 0) {
            throw new IllegalArgumentException("Stun duration cannot be negative");
        }
        this.damage=damage;
        this.stunDuration = stunDuration;
    }

    public void attack(Target target){
        target.takeDamage(this.damage);
        target.applyStun(stunDuration);

    }

    public int getDamage() {
        return this.damage;
    }

    public double getStunDuration() {
        return this.stunDuration;
    }

    @Override
    public void attack(Target target, Enchantable enchantments) {
        if (enchantments == null) {
            this.attack(target);
            return;
        }
        target.takeDamage(enchantments.modifyValue(this.damage));
        target.applyStun(stunDuration);
    }

    @Override
    public int getDamage(Enchantable enchantments) {
        if (enchantments == null) {
            return this.getDamage();
        }
        return enchantments.modifyValue(this.damage);
    }
}
