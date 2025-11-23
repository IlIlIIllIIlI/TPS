package fr.bts.iris.slam.systems;

import fr.bts.iris.slam.Target;

public class HealingSystem {
    protected int healing;
    public HealingSystem(int healing) {
        if (healing < 0) {
            throw new IllegalArgumentException("Heal amount cannot be negative");
        }

        this.healing = healing;
    }

    public void heal(Target target){
        target.heal(this.healing);
    }

    public int getHealing() {
        return this.healing;
    }
}
