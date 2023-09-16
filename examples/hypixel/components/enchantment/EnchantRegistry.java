package me.ashydev.iframework.hypixel.components.enchantment;

import lombok.Getter;
import me.ashydev.adventureengine.framework.item.components.enchantment.enchantments.ReignEnchantment;
import me.ashydev.adventureengine.framework.item.components.enchantment.enchantments.SharpnessEnchantment;
import me.ashydev.adventureengine.framework.item.components.enchantment.enchantments.ThunderEnchantment;
import me.ashydev.iframework.framework.types.registry.Registry;

public class EnchantRegistry extends Registry<String, Class<? extends EnchantmentComponent>> {
    @Getter public static final EnchantRegistry INSTANCE = new EnchantRegistry();

    static {
        INSTANCE.register("SHARPNESS", SharpnessEnchantment.class);
        INSTANCE.register("REIGN", ReignEnchantment.class);
        INSTANCE.register("THUNDER", ThunderEnchantment.class);
    }
}
