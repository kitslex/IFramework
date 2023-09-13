package me.ashydev.iframework.hypixel.components;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.iframework.framework.item.component.LoreComponent;
import me.ashydev.iframework.framework.item.component.serializable.NBTSerializableComponent;
import me.ashydev.iframework.framework.util.Color;
import me.ashydev.iframework.hypixel.info.ItemType;
import me.ashydev.iframework.hypixel.info.Rarity;
import me.ashydev.iframework.hypixel.info.Reforge;
import net.kyori.adventure.text.Component;

import java.util.List;

@Getter
@Setter
public class InfoComponent implements HypixelComponent, NBTSerializableComponent, LoreComponent {
    private Rarity rarity;
    private ItemType type;
    private Reforge reforge;

    public InfoComponent(Reforge reforge, Rarity rarity, ItemType type) {
        this.reforge = reforge;
        this.rarity = rarity;
        this.type = type;
    }

    @Override
    public List<Component> draw() {
        return List.of(Color.colorize(rarity.getColor() + "&l" + rarity.getName().toUpperCase() + " " + type.getName().toUpperCase()));
    }

    @Override
    public ReadWriteNBT serialize() {
        ReadWriteNBT nbt = NBT.createNBTObject();
        nbt.setString("rarity", rarity.name());
        nbt.setString("type", type.name());
        return nbt;
    }

    @Override
    public void deserialize(ReadWriteNBT readWriteNBT) {
        rarity = Rarity.valueOf(readWriteNBT.getString("rarity"));
        type = ItemType.valueOf(readWriteNBT.getString("type"));
    }
}
