package me.ashydev.iframework.basic.item.components;

import com.google.common.collect.Multimap;
import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import it.unimi.dsi.fastutil.Hash;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.iframework.framework.item.component.MetaComponent;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
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

    @Getter
    @Setter
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

        public SimpleMetaComponent build() {
            return new SimpleMetaComponent(displayName, id, material, unique, glowing, unbreakable, flags, customModelData, attributeModifiers);
        }
    }
}
