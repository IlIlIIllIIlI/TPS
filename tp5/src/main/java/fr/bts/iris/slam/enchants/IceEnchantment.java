package fr.bts.iris.slam.enchants;

import fr.bts.iris.slam.capabilities.Enchantable;

public class IceEnchantment implements Enchantable {
    double value = 1.5;
    String prefix  = "Frozen ";
    Enchantable next;

    public IceEnchantment() {
    }

    @Override
    public int modifyValue(int baseValue) {
        double result = baseValue*this.value;
        if (this.next != null) {
            result = this.next.modifyValue((int) result);
        }

        return (int)result;
    }

    @Override
    public String modifyName(String baseName) {
        String result = this.prefix+baseName;
        if (this.next != null) {
            result = this.next.modifyName(result);
        }

        return result;
    }

    @Override
    public void setNext(Enchantable next) {
        this.next = next;
    }
}
