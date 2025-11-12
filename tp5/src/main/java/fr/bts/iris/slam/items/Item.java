package fr.bts.iris.slam.items;

import fr.bts.iris.slam.capabilities.Enchantable;

public abstract class Item {
    protected String name;
    protected Enchantable enchantmentChain;
    
    public Item(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;

    }

    public void setEnchantments(Enchantable first){
        if (this.enchantmentChain == null) {
            this.enchantmentChain = first;
        } else {
            this.enchantmentChain.setNext(first);

        }
    }

    public Enchantable getEnchantments() {
        return enchantmentChain;
    }

    public String getName() {
        return this.enchantmentChain.modifyName(this.name);
    }
}