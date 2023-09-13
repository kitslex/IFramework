package me.ashydev.iframework.framework.item.component;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public interface LoreComponent extends DrawableComponent<List<Component>> {
    default boolean append() {
        return true;
    }
}
