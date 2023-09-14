package me.ashydev.iframework.hypixel;

import lombok.Getter;
import lombok.Setter;
import me.ashydev.iframework.basic.item.components.SimpleLoreComponent;
import me.ashydev.iframework.basic.item.components.SimpleMetaComponent;
import me.ashydev.iframework.framework.item.AbstractItem;
import me.ashydev.iframework.framework.item.component.ItemComponent;
import me.ashydev.iframework.framework.item.component.MetaComponent;
import me.ashydev.iframework.framework.item.component.types.ArrayComp;
import me.ashydev.iframework.hypixel.components.AbilityComponent;
import me.ashydev.iframework.hypixel.components.DescriptionComponent;
import me.ashydev.iframework.hypixel.components.InfoComponent;
import me.ashydev.iframework.hypixel.components.StatisticComponent;
import me.ashydev.iframework.hypixel.components.enchantment.EnchantmentComponent;
import me.ashydev.iframework.hypixel.components.enchantment.EnchantmentHolderComponent;
import me.ashydev.iframework.hypixel.info.Rarity;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

@Getter
@Setter
public abstract class HypixelItem extends AbstractItem {
    private DescriptionComponent description;
    private ArrayComp<StatisticComponent<?>> statistics;
    private InfoComponent info;
    private EnchantmentHolderComponent enchantments;
    private ArrayComp<AbilityComponent> abilities;
    private MetaComponent meta;

    public HypixelItem(String name, Material material, ArrayComp<StatisticComponent<?>> statistics, DescriptionComponent description, EnchantmentHolderComponent enchantments, ArrayComp<AbilityComponent> abilities, InfoComponent info) {
        super(new ArrayComp<ItemComponent>(statistics)
                .with(description)
                .with(enchantments)
                .with(abilities)
                .with(info)
                .with(new SimpleMetaComponent
                        .Builder(name.toUpperCase().replace(" ", "_"))
                        .displayName(info.getRarity().getColor() + info.getReforge().getName() + " " + name)
                        .material(material)
                        .unique(info.getRarity().ordinal() >= Rarity.RARE.ordinal())
                        .unbreakable(true)
                        .flags(ItemFlag.values())
                        .build()));

        this.description = description;
        this.statistics = statistics;
        this.enchantments = enchantments;
        this.abilities = abilities;
        this.info = info;
    }

    public boolean hasAbility(String id) {
        for (AbilityComponent ability : abilities)
            if (ability.getIdentifier().equalsIgnoreCase(id))
                return true;

        return false;
    }
}
