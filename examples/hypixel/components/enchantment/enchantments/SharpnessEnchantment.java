package me.ashydev.iframework.hypixel.components.enchantment.enchantments;

import me.ashydev.adventureengine.framework.item.components.enchantment.EnchantmentComponent;

import java.util.ArrayList;
import java.util.List;

public class SharpnessEnchantment extends EnchantmentComponent {
    public SharpnessEnchantment(int level) {
        super("Sharpness", "SHARPNESS", List.of("Increases melee damage dealt by", "&a" + (5 * level) + "%"), "&9", level, 5, 1);
    }

    public SharpnessEnchantment() {
        this(1);
    }
}
