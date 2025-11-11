package fr.bts.iris.slam.strategies;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Damaging;
import fr.bts.iris.slam.capabilities.Enchantable;

import java.util.Random;

public class CriticalDamageStrategy implements EnchantableDamaging {
    protected int damage;
    protected double critChance;

    public CriticalDamageStrategy(int damage,double critChance ) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        if (critChance < 0 || critChance >1) {
            throw new IllegalArgumentException("Critical duration cannot be negative or above 1 ");
        }
        this.damage=damage;
        this.critChance = critChance;
    }

    public void attack(Target target){
        Random rand = new Random();
        double n = rand.nextDouble(1);
        if (n>=critChance){
            target.takeDamage(this.damage);
        } else {
            target.takeDamage(this.damage*2);
        }
    }

    public int getDamage() {
        return this.damage;
    }

    public double getCritChance() {
        return this.critChance;
    }

    @Override
    public void attack(Target target, Enchantable enchantments) {
        if (enchantments == null) {
            this.attack(target);
            return;
        }
        Random rand = new Random();
        double n = rand.nextDouble(1);
        if (n>=critChance){
            target.takeDamage(enchantments.modifyValue(this.damage));
        } else {
            target.takeDamage(enchantments.modifyValue(this.damage)*2);
        }
    }

    @Override
    public int getDamage(Enchantable enchantments) {
        if (enchantments == null) {
            return this.getDamage();
        }
        return enchantments.modifyValue(this.damage);
    }
}
