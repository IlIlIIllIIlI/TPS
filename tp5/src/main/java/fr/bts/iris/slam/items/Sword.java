package fr.bts.iris.slam.items;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Damaging;
import fr.bts.iris.slam.capabilities.Enchantable;
import fr.bts.iris.slam.strategies.EnchantableDamaging;
import fr.bts.iris.slam.systems.DamageSystem;

public class Sword extends Item implements Damaging {
    protected EnchantableDamaging damage;

    public Sword(String name, EnchantableDamaging damage) {
        super(name);
        if (damage == null) {
            throw new IllegalArgumentException("Damage strategy can't be null");
        }
        this.damage = damage;
    }


    public int getDamage() {
        return this.damage.getDamage(this.getEnchantments());
    }

    public void attack(Target target){
        this.damage.attack(target,this.getEnchantments());
    }

    @Override
    public String getName() {
        return super.getName();
    }


}
