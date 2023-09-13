package me.ashydev.iframework.hypixel.components.enchantment;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.iframework.framework.item.component.DescribableComponent;
import me.ashydev.iframework.framework.item.component.IdentifiableComponent;
import me.ashydev.iframework.framework.item.component.LoreComponent;
import me.ashydev.iframework.framework.item.component.modifier.ItemModifierComponent;
import me.ashydev.iframework.framework.item.component.modifier.ModifierComponent;
import me.ashydev.iframework.framework.item.component.serializable.NBTSerializableComponent;
import me.ashydev.iframework.framework.util.Color;
import me.ashydev.iframework.hypixel.components.HypixelComponent;
import me.ashydev.iframework.hypixel.util.NumberFormat;
import net.kyori.adventure.text.Component;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EnchantmentComponent
        implements HypixelComponent, LoreComponent, Comparable<EnchantmentComponent>,
        NBTSerializableComponent, DescribableComponent, IdentifiableComponent, ItemModifierComponent {

    private String name, id;
    private List<String> description;
    private String color;

    private int level, max, min;

    public EnchantmentComponent(String name, String id, List<String> description, String color, int level, int max, int min) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.color = color;
        this.level = level;
        this.max = max;
        this.min = min;
    }

    public EnchantmentComponent(String name, String id, List<String> description, String color) {
        this(name, id, description, color, 1, 1, 1);
    }

    public EnchantmentComponent(String name, String id, List<String> description) {
        this(name, id, description, "&#1942bd");
    }

    public EnchantmentComponent(String name, List<String> description) {
        this(name, name.toUpperCase().replace(" ", "_"), description);
    }


    @Override
    public int compareTo(@NotNull EnchantmentComponent o) {
        return Integer.compare(level, o.level);
    }

    @Override
    public String getIdentifier() {
        return id;
    }

    @Override
    public List<Component> draw() {
        List<Component> components = new ArrayList<>();

        components.add(Color.colorize(getColor() + "&l" + getName() + " " + NumberFormat.roman(getLevel())));

        for (String line : description)
            components.add(Color.colorize("&7" + line));

        return components;
    }

    @Override
    public ReadWriteNBT serialize() {
        ReadWriteNBT nbt = NBT.createNBTObject();
        NBTCompound compound = ((NBTCompound) nbt).addCompound("enchantments").addCompound(getId());

        compound.setString("name", getName());
        compound.setString("id", getId());
        compound.setInteger("level", getLevel());
        compound.setInteger("max", getMax());
        compound.setInteger("min", getMin());
        compound.setString("color", getColor());
        compound.setString("description", String.join(";", getDescription()));

        return nbt;
    }

    @Override
    public void deserialize(ReadWriteNBT readWriteNBT) {
        NBTCompound compound = ((NBTCompound) readWriteNBT).getCompound("enchantments").getCompound(getId());

        setName(compound.getString("name"));
        setId(compound.getString("id"));
        setLevel(compound.getInteger("level"));
        setMax(compound.getInteger("max"));
        setMin(compound.getInteger("min"));
        setColor(compound.getString("color"));
        setDescription(List.of(compound.getString("description").split(";")));

    }

    @Override
    public ItemStack modify(ItemStack input) {
        ItemMeta meta = input.getItemMeta();

        if (meta.getEnchants().isEmpty()) {
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        input.setItemMeta(meta);
        return input;
    }
}
