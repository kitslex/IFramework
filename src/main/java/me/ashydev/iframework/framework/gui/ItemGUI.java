package me.ashydev.iframework.framework.gui;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import me.ashydev.iframework.framework.item.CustomItem;
import me.ashydev.iframework.framework.item.ItemRegistry;
import me.ashydev.iframework.framework.item.builder.ItemBuilder;
import org.bukkit.entity.Player;

public interface ItemGUI<I extends CustomItem> {
    ItemRegistry<I> getRegistry();
    ItemBuilder<I> getBuilder();
    ChestGui getGUI();

    void show(Player player);
}
