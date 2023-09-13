package me.ashydev.iframework;

import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import me.ashydev.iframework.basic.gui.SimpleItemGUI;
import me.ashydev.iframework.basic.item.builder.SimpleItemBuilder;
import me.ashydev.iframework.framework.item.ItemRegistry;
import me.ashydev.iframework.framework.item.builder.ItemBuilder;
import me.ashydev.iframework.framework.types.registry.SimpleRegistryKey;
import me.ashydev.iframework.hypixel.ExampleItem;
import me.ashydev.iframework.hypixel.HypixelItem;
import org.bukkit.Bukkit;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_20_R1.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.function.BiConsumer;

@Getter
public final class IFramework extends JavaPlugin {
    @Getter
    private static IFramework instance;

    private ItemRegistry<HypixelItem> registry;
    private ItemBuilder<HypixelItem> builder;

    @Override
    public void onEnable() {
        instance = this;
        registry = new ItemRegistry<>();
        builder = new SimpleItemBuilder<>(registry);

        registry.register("EXAMPLE_ITEM", new ExampleItem());

        registry.setup(this);

        registerCommand("gui", (player, args) -> {
            SimpleItemGUI<HypixelItem> gui = new SimpleItemGUI<>(registry, builder);
            gui.show(player);
        });

        registerCommand("nbt", (player, args) -> {
            ItemStack stack = player.getInventory().getItemInMainHand();
            if (stack == null || stack.getType().isAir()) {
                player.sendMessage("You must be holding an item!");
                return;
            }

            NBTItem nbt = new NBTItem(stack);
            player.sendMessage(nbt.toString());
        });
    }

    void registerCommand(String name, BiConsumer<Player, List<String>> consumer) {
        BukkitCommand command = new BukkitCommand(name) {
            @Override
            public boolean execute(org.bukkit.command.CommandSender sender, String commandLabel, String[] args) {
                if (sender instanceof Player) {
                    consumer.accept((Player) sender, List.of(args));
                }
                return true;
            }
        };

        ((CraftServer) Bukkit.getServer()).getCommandMap().register(name, command);
    }
}
