package me.ashydev.iframework.basic.item.builder;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import me.ashydev.iframework.framework.item.CustomItem;
import me.ashydev.iframework.framework.item.ItemRegistry;
import me.ashydev.iframework.framework.item.builder.ItemBuilder;
import me.ashydev.iframework.framework.item.component.ItemComponent;
import me.ashydev.iframework.framework.item.component.LoreComponent;
import me.ashydev.iframework.framework.item.component.MetaComponent;
import me.ashydev.iframework.framework.item.component.modifier.ItemModifierComponent;
import me.ashydev.iframework.framework.item.component.serializable.NBTSerializableComponent;
import me.ashydev.iframework.framework.item.component.types.ArrayComp;
import me.ashydev.iframework.framework.util.Color;
import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;
import java.util.stream.Stream;

public class SimpleItemBuilder<T extends CustomItem> implements ItemBuilder<T> {
    private final ItemRegistry<T> registry;

    public SimpleItemBuilder(ItemRegistry<T> registry) {
        this.registry = registry;
    }

    @Override
    public ItemStack build(T input) {
        ArrayComp<ItemComponent> components = input.getComponents();

        MetaComponent meta = components
                .getItemsOfType(MetaComponent.class)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Item must have meta component!"));

        ItemStack stack = new ItemStack(meta.getMaterial());
        ItemMeta itemMeta = stack.getItemMeta();

        buildMeta(input, meta, itemMeta);
        stack.setItemMeta(itemMeta);

        buildNBT(stack, input);

        components
                .getItemsOfType(ItemModifierComponent.class)
                .filter(Objects::nonNull)
                .forEach(comp -> comp.modify(stack));


        return stack;
    }

    void buildNBT(ItemStack stack, T input) {
        NBTItem nbt = new NBTItem(stack);

        NBTCompound compound = nbt.getOrCreateCompound("Item-Framework");

        input.getComponents().getItemsOfType(NBTSerializableComponent.class).forEach(comp -> {
            ReadWriteNBT object = comp.serialize();
            compound.mergeCompound(object);
        });

        nbt.applyNBT(stack);
    }

    void buildMeta(T input, MetaComponent component, ItemMeta meta) {
        meta.displayName(Color.colorize(component.getDisplayName()));

        ArrayComp<LoreComponent> comp = input
                .getComponents()
                .getItemsOfType(LoreComponent.class, true);

        // ArrayComp<DrawableComponent<?>> drawables = comp.getDrawables(true);
        ArrayList<Component> lore = new ArrayList<>();

        ArrayComp<LoreComponent> loreComponents = comp.getItemsOfType(LoreComponent.class, true);

        for (int i = 0; i < loreComponents.size(); i++) {
            LoreComponent loreComponent = loreComponents.get(i);
            lore.addAll(loreComponent.draw());
            if (loreComponent.append() && i != loreComponents.size() - 1)
                lore.add(Component.text(" "));
        }

        meta.lore(lore);

        if (component.isGlowing()) {
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }

        if (component.isUnbreakable()) {
            meta.setUnbreakable(true);
            meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        }

        if (component.getFlags() != null) {
            meta.addItemFlags(component.getFlags());
        }

        if (component.getCustomModelData() != 0) {
            meta.setCustomModelData(component.getCustomModelData());
        }

        if (!component.getAttributeModifiers().isEmpty())
            for (Map.Entry<Attribute, Collection<AttributeModifier>> entry : component.getAttributeModifiers().entrySet())
                for (AttributeModifier modifier : entry.getValue())
                    meta.addAttributeModifier(entry.getKey(), modifier);
    }

    @Override
    public T from(ItemStack output) {
        if (output == null || output.getType().isAir()) return null;

        NBTItem nbt = new NBTItem(output);
        NBTCompound compound = nbt.getCompound("Item-Framework");

        if (compound == null) return null;

        String id = compound.getString("id");
        T item = registry.get(id);
        item.getComponents().clear();

        ArrayComp<NBTSerializableComponent> components = registry.getComponents().getItemsOfType(NBTSerializableComponent.class, true);

        for (NBTSerializableComponent component : components) {
            component.deserialize(compound);
            item.getComponents().add(component);
        }


        return item;
    }

    @Override
    public ItemStack[] buildAll(T... inputs) {
        ItemStack[] stacks = new ItemStack[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            stacks[i] = build(inputs[i]);
        }

        return stacks;
    }

    @Override
    public Collection<ItemStack> buildAll(Collection<T> input) {
        List<ItemStack> stacks = new ArrayList<>();

        for (T item : input) {
            stacks.add(build(item));
        }

        return stacks;
    }

    @Override
    public ItemStack update(ItemStack output) {
        T item = from(output);
        return build(item);
    }
}
