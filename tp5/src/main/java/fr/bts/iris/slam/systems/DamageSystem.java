package fr.bts.iris.slam.systems;


import fr.bts.iris.slam.Target;

public class DamageSystem {
    protected int damage;
    public DamageSystem(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        this.damage=damage;
    }

    public int hit(Target target){
        target.takeDamage(this.damage);
        return this.damage;
    }

    public int getDamage() {
        return damage;
    }
}
