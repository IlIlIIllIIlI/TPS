package fr.bts.iris.slam.items;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Damaging;
import fr.bts.iris.slam.systems.DamageSystem;

public class PoisonPotion extends Item implements Damaging {
    protected DamageSystem damageSystem;

    public PoisonPotion(String name, int damage) {
        super(name);
        this.damageSystem = new DamageSystem(damage);
    }

    public int getDamage() {
        return this.damageSystem.getDamage();
    }

    public void attack(Target target){
         damageSystem.hit(target);
    }

    @Override
    public String getName() {
        return super.getName();
    }
}
