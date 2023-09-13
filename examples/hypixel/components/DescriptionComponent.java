package me.ashydev.iframework.hypixel.components;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.iframework.framework.item.component.DescribableComponent;
import me.ashydev.iframework.framework.item.component.DrawableComponent;
import me.ashydev.iframework.framework.item.component.LoreComponent;
import me.ashydev.iframework.framework.item.component.serializable.NBTSerializableComponent;
import me.ashydev.iframework.framework.util.Color;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DescriptionComponent implements HypixelComponent, NBTSerializableComponent, LoreComponent, DescribableComponent {
    private List<String> description;

    public DescriptionComponent(List<String> description) {
        this.description = description;
    }

    public DescriptionComponent(String... description) {
        this.description = List.of(description);
    }

    @Override
    public List<Component> draw() {
        List<Component> components = new ArrayList<>();

        for (String line : description)
            components.add(Color.colorize("&7" + line));

        return components;
    }

    @Override
    public ReadWriteNBT serialize() {
        ReadWriteNBT nbt = NBT.createNBTObject();
        nbt.setString("description", String.join(";", description));
        return nbt;
    }

    @Override
    public void deserialize(ReadWriteNBT readWriteNBT) {
        description = List.of(readWriteNBT.getString("description").split(";"));
    }
}
