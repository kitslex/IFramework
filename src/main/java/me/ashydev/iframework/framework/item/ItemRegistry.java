package me.ashydev.iframework.framework.item;

import me.ashydev.iframework.framework.item.component.ActionComponent;
import me.ashydev.iframework.framework.types.registry.SimpleRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class ItemRegistry<I extends CustomItem> extends SimpleRegistry<String, I> {
    public void setup(Plugin plugin) {
        for (I i : getValues()) {
            i.getComponents()
                    .getItemsOfType(ActionComponent.class)
                    .forEach((actionComponent -> Bukkit.getPluginManager().registerEvents(actionComponent, plugin)));

            i.getComponents()
                    .getItemsOfType(InitializeComponent.class)
                    .forEach(InitializeComponent::initialize);
        }
    }
}
