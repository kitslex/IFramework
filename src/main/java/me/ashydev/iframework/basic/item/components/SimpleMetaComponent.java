package me.ashydev.iframework.basic.item.components;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import me.ashydev.iframework.framework.item.component.MetaComponent;
import me.ashydev.iframework.framework.item.component.serializable.SerializableComponent;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class SimpleMetaComponent implements MetaComponent {
    private boolean unique;
    private boolean glowing;
    private boolean unbreakable;
    private ItemFlag[] flags;
    private int customModelData;
    private String displayName;
    private HashMap<Attribute, Collection<AttributeModifier>> attributeModifiers;
    private Material material;

    private String id;

    public SimpleMetaComponent(String name, String id, Material material, boolean unique, boolean glowing, boolean unbreakable, ItemFlag[] flags, int customModelData, HashMap<Attribute, Collection<AttributeModifier>> attributeModifiers) {
        this.id = id;
        this.displayName = name;
        this.material = material;
        this.unique = unique;
        this.glowing = glowing;
        this.unbreakable = unbreakable;
        this.flags = flags;
        this.customModelData = customModelData;
        this.attributeModifiers = attributeModifiers;
    }


    @Override
    public boolean isUnique() {
        return unique;
    }

    @Override
    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    @Override
    public boolean isGlowing() {
        return glowing;
    }

    @Override
    public void setGlowing(boolean glowing) {
        this.glowing = glowing;
    }

    @Override
    public boolean isUnbreakable() {
        return unbreakable;
    }

    @Override
    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    @Override
    public ItemFlag[] getFlags() {
        return flags;
    }

    @Override
    public void setFlags(ItemFlag[] flags) {
        this.flags = flags;
    }

    @Override
    public int getCustomModelData() {
        return customModelData;
    }

    @Override
    public void setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public HashMap<Attribute, Collection<AttributeModifier>> getAttributeModifiers() {
        return attributeModifiers;
    }

    @Override
    public void setAttributeModifiers(HashMap<Attribute, Collection<AttributeModifier>> attributeModifiers) {
        this.attributeModifiers = attributeModifiers;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getIdentifier() {
        return id;
    }

    @Override
    public ItemStack modify(ItemStack input) {
        return null;
    }

    @Override
    public String getName() {
        return displayName;
    }

    @Override
    public ReadWriteNBT serialize() {
        ReadWriteNBT object = NBT.createNBTObject();
        object.setString("id", getIdentifier());

        if (unique)
            object.setUUID("UniqueIdentifier", UUID.randomUUID());

        return object;
    }

    @Override
    public void deserialize(ReadWriteNBT compound) {
        id = compound.getString("id");
        unique = compound.hasTag("UniqueIdentifier");
    }

    @Override
    public SerializableComponent<ReadWriteNBT> clone() {
        return new SimpleMetaComponent(displayName, id, material, unique, glowing, unbreakable, flags, customModelData, attributeModifiers);
    }


    public static class Builder {
        private final String id;
        private Material material = Material.STONE;
        private boolean unique = false;
        private boolean glowing = false;
        private boolean unbreakable = false;
        private ItemFlag[] flags = new ItemFlag[0];
        private int customModelData = -1;
        private String displayName;
        private HashMap<Attribute, Collection<AttributeModifier>> attributeModifiers = new HashMap<>();

        public Builder(String id) {
            this.id = id;
        }

        public Builder material(Material material) {
            this.material = material;
            return this;
        }


        public Builder unique(boolean unique) {
            this.unique = unique;
            return this;
        }

        public Builder glowing(boolean glowing) {
            this.glowing = glowing;
            return this;
        }

        public Builder unbreakable(boolean unbreakable) {
            this.unbreakable = unbreakable;
            return this;
        }

        public Builder flags(ItemFlag... flags) {
            this.flags = flags;
            return this;
        }

        public Builder customModelData(int customModelData) {
            this.customModelData = customModelData;
            return this;
        }

        public Builder displayName(String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder attributeModifiers(HashMap<Attribute, Collection<AttributeModifier>> attributeModifiers) {
            this.attributeModifiers = attributeModifiers;
            return this;
        }


        public String getId() {
            return id;
        }

        public Material getMaterial() {
            return material;
        }

        public void setMaterial(Material material) {
            this.material = material;
        }

        public boolean isUnique() {
            return unique;
        }

        public void setUnique(boolean unique) {
            this.unique = unique;
        }

        public boolean isGlowing() {
            return glowing;
        }

        public void setGlowing(boolean glowing) {
            this.glowing = glowing;
        }

        public boolean isUnbreakable() {
            return unbreakable;
        }

        public void setUnbreakable(boolean unbreakable) {
            this.unbreakable = unbreakable;
        }

        public ItemFlag[] getFlags() {
            return flags;
        }

        public void setFlags(ItemFlag[] flags) {
            this.flags = flags;
        }

        public int getCustomModelData() {
            return customModelData;
        }

        public void setCustomModelData(int customModelData) {
            this.customModelData = customModelData;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public HashMap<Attribute, Collection<AttributeModifier>> getAttributeModifiers() {
            return attributeModifiers;
        }

        public void setAttributeModifiers(HashMap<Attribute, Collection<AttributeModifier>> attributeModifiers) {
            this.attributeModifiers = attributeModifiers;
        }

        public SimpleMetaComponent build() {
            return new SimpleMetaComponent(displayName, id, material, unique, glowing, unbreakable, flags, customModelData, attributeModifiers);
        }
    }
}
