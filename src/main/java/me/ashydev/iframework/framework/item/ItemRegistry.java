package me.ashydev.iframework.framework.item;

import me.ashydev.iframework.framework.item.component.ActionComponent;
import me.ashydev.iframework.framework.item.component.ItemComponent;
import me.ashydev.iframework.framework.item.component.serializable.SerializableComponent;
import me.ashydev.iframework.framework.item.component.types.ArrayComp;
import me.ashydev.iframework.framework.types.registry.SimpleRegistry;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ItemRegistry<I extends CustomItem> extends SimpleRegistry<String, I> {
    private ArrayComp<ItemComponent> components = new ArrayComp<>();

    public ArrayComp<ItemComponent> getComponents() {
        return components;
    }

    public void setComponents(ArrayComp<ItemComponent> components) {
        this.components = components;
    }

    public void setup(Plugin plugin) {
        for (I i : getValues()) {
            i.getComponents()
                    .getItemsOfType(ActionComponent.class)
                    .forEach((actionComponent -> Bukkit.getPluginManager().registerEvents(actionComponent, plugin)));

            i.getComponents()
                    .getItemsOfType(InitializeComponent.class)
                    .forEach(InitializeComponent::initialize);

            i.getComponents()
                    .getItemsOfType(ItemComponent.class)
                    .filter((component -> !components.contains(component)))
                    .forEach(components::add);
        }
    }
}
