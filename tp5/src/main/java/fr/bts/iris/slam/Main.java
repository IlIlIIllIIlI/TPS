package fr.bts.iris.slam;

import fr.bts.iris.slam.enchants.EnchantmentChainBuilder;
import fr.bts.iris.slam.enchants.FireEnchantment;
import fr.bts.iris.slam.enchants.HolyEnchantment;
import fr.bts.iris.slam.enchants.IceEnchantment;
import fr.bts.iris.slam.items.Sword;
import fr.bts.iris.slam.strategies.MeleeDamageStrategy;

public class Main {
    public static void main(String[] args) {
        Sword sword = new Sword("Iron Blade", new MeleeDamageStrategy(20));
        Target target = new Target(150);

        EnchantmentChainBuilder enchants = new EnchantmentChainBuilder();


        System.out.println(sword.getDamage());


       enchants = enchants.add(new FireEnchantment(20));

        enchants = enchants.add(new IceEnchantment());

        enchants = enchants.add(new HolyEnchantment());

        enchants = enchants.add(new IceEnchantment());

        sword.setEnchantments(enchants.build());


        System.out.println(sword.getDamage());

        sword.attack(target);

        System.out.println(target.getCurrentHealth());
    }
}
