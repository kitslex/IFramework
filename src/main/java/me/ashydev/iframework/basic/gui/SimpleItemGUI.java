package me.ashydev.iframework.basic.gui;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import de.tr7zw.nbtapi.NBTItem;
import lombok.Getter;
import lombok.Setter;
import me.ashydev.iframework.IFramework;
import me.ashydev.iframework.basic.item.CommonItem;
import me.ashydev.iframework.framework.gui.ItemGUI;
import me.ashydev.iframework.framework.item.CustomItem;
import me.ashydev.iframework.framework.item.ItemRegistry;
import me.ashydev.iframework.framework.item.builder.ItemBuilder;
import net.kyori.adventure.sound.Sound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class SimpleItemGUI<T extends CustomItem> implements ItemGUI<T> {
    private ItemRegistry<T> registry;
    private ItemBuilder<T> builder;
    private ChestGui gui;

    public SimpleItemGUI(ItemRegistry<T> registry, ItemBuilder<T> builder) {
        this.registry = registry;
        this.builder = builder;

        setup();
    }

    void setup() {
        gui = new ChestGui(6, "Items");


        gui.setOnGlobalClick(event -> event.setCancelled(true));

        PaginatedPane pages = new PaginatedPane(0, 0, 9, 5);
        pages.populateWithItemStacks((List<ItemStack>) builder.buildAll(registry.getValues()));
        pages.setOnClick(event -> {
            if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR)
                return;

            event.setCancelled(true);

            event.getWhoClicked().getInventory().addItem(event.getCurrentItem());
            event.getWhoClicked().playSound(Sound.sound(org.bukkit.Sound.BLOCK_NOTE_BLOCK_PLING, Sound.Source.MASTER, 1, 2));
        });

        gui.addPane(pages);

        OutlinePane background = new OutlinePane(0, 5, 9, 1);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);
        background.setPriority(Pane.Priority.LOWEST);

        gui.addPane(background);

        StaticPane navigation = new StaticPane(0, 5, 9, 1);
        navigation.addItem(new GuiItem(new ItemStack(Material.ARROW), event -> {
            if (pages.getPage() > 0) {
                pages.setPage(pages.getPage() - 1);

                gui.update();
            }
        }), 0, 0);

        navigation.addItem(new GuiItem(new ItemStack(Material.ARROW), event -> {
            if (pages.getPage() < pages.getPages() - 1) {
                pages.setPage(pages.getPage() + 1);

                gui.update();
            }
        }), 8, 0);

        navigation.addItem(new GuiItem(new ItemStack(Material.BARRIER), event ->
                event.getWhoClicked().closeInventory()), 4, 0);

        gui.addPane(navigation);

    }

    @Override
    public ChestGui getGUI() {
        return gui;
    }

    @Override
    public void show(Player player) {
        gui.show(player);
    }
}
