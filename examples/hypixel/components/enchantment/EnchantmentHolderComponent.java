package me.ashydev.iframework.hypixel.components.enchantment;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import me.ashydev.iframework.framework.item.component.LoreComponent;
import me.ashydev.iframework.framework.item.component.modifier.ItemModifierComponent;
import me.ashydev.iframework.framework.item.component.serializable.NBTSerializableComponent;
import me.ashydev.iframework.framework.item.component.types.ArrayComp;
import me.ashydev.iframework.framework.types.IContainer;
import me.ashydev.iframework.framework.util.Color;
import me.ashydev.iframework.hypixel.components.HypixelComponent;
import me.ashydev.iframework.hypixel.util.NumberFormat;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Container;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentHolderComponent
        implements HypixelComponent, LoreComponent, IContainer<ArrayComp<EnchantmentComponent>>, ItemModifierComponent, NBTSerializableComponent {
    private ArrayComp<EnchantmentComponent> enchantments;

    public EnchantmentHolderComponent(ArrayComp<EnchantmentComponent> enchantments) {
        this.enchantments = enchantments;
    }

    public EnchantmentHolderComponent() {
        this(new ArrayComp<>());
    }

    public EnchantmentHolderComponent(EnchantmentComponent... enchantments) {
        this(new ArrayComp<>(enchantments));
    }

    public EnchantmentHolderComponent add(EnchantmentComponent enchantment) {
        this.enchantments.add(enchantment);
        return this;
    }

    public EnchantmentHolderComponent remove(EnchantmentComponent enchantment) {
        this.enchantments.remove(enchantment);
        return this;
    }

    public EnchantmentHolderComponent clear() {
        this.enchantments.clear();
        return this;
    }

    @Override
    public ArrayComp<EnchantmentComponent> get() {
        return enchantments;
    }

    @Override
    public void set(ArrayComp<EnchantmentComponent> value) {
        this.enchantments = value;
    }

    @Override
    public List<Component> draw() {
        List<Component> lore = new ArrayList<>();

        int size = enchantments.size();

        if (size <= 6) {
            for (EnchantmentComponent enchantment : enchantments) {
                lore.addAll(enchantment.draw());
            }
        } else if (size <= 16) {
            for (EnchantmentComponent enchantment : enchantments) {
                lore.add(Color.colorize(enchantment.getColor() + "&l" + enchantment.getName() + " " + NumberFormat.roman(enchantment.getLevel())));
            }
        } else {
            Component component = Component.empty();
            for (int i = 0; i < enchantments.size(); i++) {
                EnchantmentComponent enchantment = enchantments.get(i);
                Component cx = Color.colorize(enchantment.getColor() + "&l" + enchantment.getName() + " " + NumberFormat.roman(enchantment.getLevel()));
                component = component.append(cx);

                if (i != enchantments.size() - 1) {
                    component.append(Color.colorize(", "));

                    if ((i + 1) % 3 == 0) {
                        lore.add(component);
                        component = Component.empty();
                    } else if (i == enchantments.size() - 1) {
                        lore.add(component);
                    }
                }
            }
        }

        return lore;
    }

    @Override
    public ItemStack modify(ItemStack input) {
        for (EnchantmentComponent enchantment : enchantments)
            enchantment.modify(input);

        return input;
    }

    @Override
    public ReadWriteNBT serialize() {
        ReadWriteNBT nbt = NBT.createNBTObject();
        for (EnchantmentComponent enchantment : enchantments)
            nbt.mergeCompound(enchantment.serialize());

        return nbt;
    }

    @Override
    public void deserialize(ReadWriteNBT readWriteNBT) {
        NBTCompound compound = (NBTCompound) readWriteNBT;
        for (String key : compound.getCompound("enchantments").getKeys()) {
            NBTCompound enchantment = compound.getCompound("enchantments").getCompound(key);
            EnchantmentComponent component = new EnchantmentComponent(
                    enchantment.getString("name"),
                    enchantment.getString("id"),
                    List.of(enchantment.getString("description").split(";")),
                    enchantment.getString("color"),
                    enchantment.getInteger("level"),
                    enchantment.getInteger("max"),
                    enchantment.getInteger("min")
            );

            enchantments.add(component);
        }
    }
}
