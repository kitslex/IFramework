package me.ashydev.iframework.hypixel.components;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.iframework.framework.item.InitializeComponent;
import me.ashydev.iframework.framework.item.component.ActionComponent;
import me.ashydev.iframework.framework.item.component.DescribableComponent;
import me.ashydev.iframework.framework.item.component.IdentifiableComponent;
import me.ashydev.iframework.framework.item.component.LoreComponent;
import me.ashydev.iframework.framework.item.component.serializable.NBTSerializableComponent;
import me.ashydev.iframework.framework.util.Color;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.*;

@Getter
@Setter
public class AbilityComponent implements HypixelComponent, InitializeComponent, ActionComponent, NBTSerializableComponent, LoreComponent, IdentifiableComponent, DescribableComponent {
    private String name, identifier, action;
    private List<String> description;
    private int cooldown;

    private HashMap<UUID, Long> cooldowns = new HashMap<>();

    public AbilityComponent(String name, String identifier, String action, List<String> description, int cooldown) {
        this.name = name;
        this.action = action;
        this.identifier = identifier;
        this.description = description;
        this.cooldown = cooldown;
    }

    public AbilityComponent(String name, String action, String identifier, List<String> description) {
        this(name, identifier, action, description, 0);
    }

    @Override
    public List<Component> draw() {
        List<Component> components = new ArrayList<>();

        components.add(Color.colorize("&#e6871cItem Ability: " + getName() + " &e&l" + action.toUpperCase()));

        for (String line : description) {
            components.add(Color.colorize("&7" + line));
        }

        if (cooldown > 0) {
            components.add(Color.colorize("&8Cooldown: &3" + cooldown / 1000 + "s"));
        }

        return components;
    }

    @Override
    public ReadWriteNBT serialize() {
        ReadWriteNBT nbt = NBT.createNBTObject();
        NBTCompound compound = (NBTCompound) nbt.getOrCreateCompound("ability").getOrCreateCompound(identifier);


        compound.setString("name", name);
        compound.setString("identifier", identifier);
        compound.setString("action", action);
        compound.setInteger("cooldown", cooldown);
        compound.setString("description", String.join(";", description));

        return nbt;
    }

    @Override
    public void deserialize(ReadWriteNBT readWriteNBT) {
        NBTCompound compound = (NBTCompound) readWriteNBT.getCompound("ability").getCompound(identifier);

        name = compound.getString("name");
        identifier = compound.getString("identifier");
        action = compound.getString("action");
        cooldown = compound.getInteger("cooldown");
        description = List.of(compound.getString("description").split(";"));
    }

    @Override
    public void initialize() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                cooldowns.forEach((uuid, time) -> {
                    if (System.currentTimeMillis() - time >= cooldown)
                        cooldowns.remove(uuid);

                });
            }
        };

        Timer timer = new Timer();

        timer.schedule(task, 0, 250);
    }

    public boolean hasCooldown(UUID uuid) {
        return cooldowns.containsKey(uuid);
    }

    public long getCooldown(UUID uuid) {
        return cooldowns.get(uuid);
    }

    public void sendCooldownMessage(Player player) {
        long time = getCooldown(player.getUniqueId());
        long seconds = (time - System.currentTimeMillis()) / 1000;

        if (seconds <= 0) {
            return;
        }

        long minutes = seconds / 60;
        seconds = seconds % 60;

        String message = "&cYou must wait &e" + minutes + "m " + seconds + "s &cbefore using this ability again!";

        player.sendMessage(Color.colorize(message));
    }

    public void setCooldown(UUID uuid) {
        cooldowns.put(uuid, System.currentTimeMillis());
    }

}
