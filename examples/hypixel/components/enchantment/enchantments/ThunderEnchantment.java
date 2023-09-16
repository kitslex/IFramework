package me.ashydev.iframework.hypixel.components.enchantment.enchantments;

import me.ashydev.adventureengine.framework.item.components.enchantment.EnchantmentComponent;
import me.ashydev.adventureengine.framework.item.util.Mathf;
import me.ashydev.adventureengine.framework.item.util.NumberFormat;

import java.util.List;

public class ThunderEnchantment extends EnchantmentComponent {
    public ThunderEnchantment(int level) {
        super("Thunder", "THUNDER", List.of("Has a &a" + NumberFormat.comma(Mathf.lerp(0, 100, (float) level / 18)) + "% &7chance", "to &bstrike lightning&7 on hit"), "&9", level, 10, 1);
    }

    public ThunderEnchantment() {
        this(1);
    }
}

