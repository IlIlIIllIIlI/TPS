package fr.bts.iris.slam.capabilities;

import fr.bts.iris.slam.Target;

public interface Healing {
    void heal(Target target);
    int getHealPower();
}
