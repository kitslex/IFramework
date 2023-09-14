package me.ashydev.iframework.hypixel.components;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.iframework.framework.item.component.IdentifiableComponent;
import me.ashydev.iframework.framework.item.component.LoreComponent;
import me.ashydev.iframework.framework.item.component.serializable.NBTSerializableComponent;
import me.ashydev.iframework.framework.util.Color;
import net.kyori.adventure.text.Component;

import java.util.List;

@Getter
@Setter
public class StatisticComponent<T> implements IdentifiableComponent, NBTSerializableComponent, HypixelComponent, LoreComponent {
    private T value;
    private String name, id, operator;
    private OperatorPosition operatorPosition;
    private Color color;
    public StatisticComponent(String name, String id, T value, Color color, String operator, OperatorPosition operatorPosition) {
        this.name = name;
        this.id = id;
        this.value = value;
        this.color = color;
        this.operator = operator;
        this.operatorPosition = operatorPosition;
    }

    @Override
    public String getIdentifier() {
        return id;
    }

    @Override
    public List<Component> draw() {
        return List.of(operatorPosition == OperatorPosition.PREFIX
                ? Color.colorize("&7" + name + ": " + color.getHex("&#") + operator + value)
                : Color.colorize("&7" + name + ": " + color.getHex("&#") + value + operator));
    }

    @Override
    public ReadWriteNBT serialize() {
        ReadWriteNBT nbt = NBT.createNBTObject();
        NBTCompound compound = (NBTCompound) nbt;
        nbt.setString("id", id);
        nbt.setString("name", name);
        nbt.setString("operator", operator);
        nbt.setString("operatorPosition", operatorPosition.name());
        nbt.setString("color", color.getHex("#"));
        compound.setObject("value", value);
        return nbt;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void deserialize(ReadWriteNBT readWriteNBT) {
        NBTCompound compound = (NBTCompound) readWriteNBT;
        id = readWriteNBT.getString("id");
        name = readWriteNBT.getString("name");
        operator = readWriteNBT.getString("operator");
        operatorPosition = OperatorPosition.valueOf(readWriteNBT.getString("operatorPosition"));
        color = Color.fromHex(readWriteNBT.getString("color"));
        value = (T) compound.getObject("value", Object.class);
    }

    public enum OperatorPosition {
        PREFIX,
        SUFFIX
    }
}
