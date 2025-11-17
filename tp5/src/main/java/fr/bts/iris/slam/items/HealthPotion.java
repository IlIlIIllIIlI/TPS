package fr.bts.iris.slam.items;

import fr.bts.iris.slam.Target;
import fr.bts.iris.slam.capabilities.Healing;
import fr.bts.iris.slam.systems.HealingSystem;

public class HealthPotion extends Item implements Healing {
    protected HealingSystem healingSystem;

    public HealthPotion(String name, int healing){
        super(name);
        this.healingSystem = new HealingSystem(healing);

    }
    @Override
    public void heal(Target target){
         this.healingSystem.heal(target);
    }
    @Override
    public int getHealPower(){
        return this.healingSystem.getHealing();
    }
}
