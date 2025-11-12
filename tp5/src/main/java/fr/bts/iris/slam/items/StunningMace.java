package fr.bts.iris.slam.items;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Damaging;
import fr.bts.iris.slam.capabilities.Enchantable;
import fr.bts.iris.slam.strategies.EnchantableDamaging;

public class StunningMace extends Item implements Damaging {
    protected EnchantableDamaging damage;

    public StunningMace(String name, EnchantableDamaging damage) {
        super(name);
        if (damage == null) {
            throw new IllegalArgumentException("Damage strategy can't be null");
        }
        this.damage = damage;
    }
    @Override
    public int getDamage() {
        return this.damage.getDamage(this.getEnchantments());
    }
    @Override
    public void attack(Target target){
        this.damage.attack(target,this.getEnchantments());
    }

    public String getName() {
        return super.getName();
    }
}
