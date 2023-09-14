package me.ashydev.iframework.framework.item.component;

import me.ashydev.iframework.framework.item.component.modifier.ModifierComponent;
import me.ashydev.iframework.framework.item.component.serializable.NBTSerializableComponent;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;

public interface MetaComponent extends NBTSerializableComponent, IdentifiableComponent, ModifierComponent<ItemStack, ItemStack> {

    Material getMaterial();

    void setMaterial(Material material);

    boolean isUnique();

    void setUnique(boolean unique);

    boolean isGlowing();

    void setGlowing(boolean glowing);

    boolean isUnbreakable();

    void setUnbreakable(boolean unbreakable);

    ItemFlag[] getFlags();

    void setFlags(ItemFlag... flags);

    int getCustomModelData();

    void setCustomModelData(int customModelData);

    String getDisplayName();

    void setDisplayName(String displayName);

    HashMap<Attribute, Collection<AttributeModifier>> getAttributeModifiers();

    void setAttributeModifiers(HashMap<Attribute, Collection<AttributeModifier>> attributeModifiers);

}
