package me.ashydev.iframework.hypixel;

import me.ashydev.iframework.framework.item.component.types.ArrayComp;
import me.ashydev.iframework.hypixel.components.DescriptionComponent;
import me.ashydev.iframework.hypixel.components.InfoComponent;
import me.ashydev.iframework.hypixel.components.StatisticComponent;
import me.ashydev.iframework.hypixel.components.abilities.InstantTransmissionAbility;
import me.ashydev.iframework.hypixel.components.enchantment.EnchantmentContainer;
import me.ashydev.iframework.hypixel.components.enchantment.enchantments.SharpnessEnchantment;
import me.ashydev.iframework.hypixel.components.stats.DamageStatistic;
import me.ashydev.iframework.hypixel.info.ItemType;
import me.ashydev.iframework.hypixel.info.Rarity;
import me.ashydev.iframework.hypixel.info.Reforge;
import org.bukkit.Material;

public class ExampleItem extends HypixelItem {
    public ExampleItem() {
        super("Example Item",
                Material.DIAMOND_SWORD,
                new ArrayComp<>(
                        new DamageStatistic(500.0)
                ),
                new DescriptionComponent("This is an example item."),
                new EnchantmentContainer(
                        new SharpnessEnchantment(5)
                ),
                new ArrayComp<>(
                        new InstantTransmissionAbility(8)
                ),
                new InfoComponent(Reforge.FABLED, Rarity.MYTHIC, ItemType.SWORD)
        );
    }
}
