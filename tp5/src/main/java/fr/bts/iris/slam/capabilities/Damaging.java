package fr.bts.iris.slam.capabilities;

import fr.bts.iris.slam.Target;

public interface Damaging {
    void attack(Target target);
    int getDamage();
}
