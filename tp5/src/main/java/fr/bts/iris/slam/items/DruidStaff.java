package fr.bts.iris.slam.items;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Damaging;
import fr.bts.iris.slam.capabilities.Enchantable;
import fr.bts.iris.slam.capabilities.Healing;
import fr.bts.iris.slam.strategies.EnchantableDamaging;
import fr.bts.iris.slam.strategies.EnchantableHealing;
import fr.bts.iris.slam.systems.DamageSystem;
import fr.bts.iris.slam.systems.HealingSystem;

public class DruidStaff extends Item implements Damaging,Healing {
    protected EnchantableHealing healing;
    protected EnchantableDamaging damage;

    public DruidStaff(String name, EnchantableDamaging damage, EnchantableHealing heal) {
        super(name);
        if (damage == null) {
            throw new IllegalArgumentException("Damage strategy can't be null");
        }
        if (heal == null) {
            throw new IllegalArgumentException("Healing strategy can't be null");
        }
        this.damage = damage;
        this.healing = heal;
    }

    public void heal(Target target){
        this.healing.heal(target,this.getEnchantments());
    }

    public int getDamage() {
        return this.damage.getDamage(this.getEnchantments());
    }

    public void attack(Target target){
        damage.attack(target,this.getEnchantments());
    }

    public int getHealPower(){
        return this.healing.getHealPower(this.getEnchantments());
    }
    @Override
    public String getName() {
        return super.getName();
    }


}
