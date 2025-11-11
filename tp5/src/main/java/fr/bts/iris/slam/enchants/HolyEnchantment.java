package fr.bts.iris.slam.enchants;

import fr.bts.iris.slam.capabilities.Enchantable;

public class HolyEnchantment implements Enchantable {
    int value = 20;
    String prefix  = "Holy ";
    Enchantable next;

    public HolyEnchantment() {
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
