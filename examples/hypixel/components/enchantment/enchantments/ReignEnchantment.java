package me.ashydev.iframework.hypixel.components.enchantment.enchantments;

import me.ashydev.adventureengine.framework.item.components.enchantment.EnchantmentComponent;
import me.ashydev.adventureengine.framework.item.util.NumberFormat;

import java.util.List;

public class ReignEnchantment extends EnchantmentComponent {
    public ReignEnchantment(int level) {
        super("Reign", "REIGN", List.of("Unknown ? ? ? &a" + NumberFormat.format(25 * level, 25_000)), "&9", level, 25, 1);
    }

    public ReignEnchantment() {
        this(1);
    }
}
