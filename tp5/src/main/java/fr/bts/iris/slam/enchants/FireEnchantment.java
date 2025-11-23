package fr.bts.iris.slam.enchants;

import fr.bts.iris.slam.capabilities.Enchantable;

public class FireEnchantment implements Enchantable {
    int value;
    String prefix  = "Flaming ";
    Enchantable next;

    public FireEnchantment(int value) {
        if (value<0){
            throw new IllegalArgumentException("Value can't be negative");
        }
        this.value = value;
    }

    @Override
    public int modifyValue(int baseValue) {
        int result = baseValue+this.value;
        if (this.next != null) {
            result = this.next.modifyValue(result);
        }

        return result;
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
