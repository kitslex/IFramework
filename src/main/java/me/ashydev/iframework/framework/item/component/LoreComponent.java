package me.ashydev.iframework.framework.item.component;

import net.kyori.adventure.text.Component;

import java.util.List;

public interface LoreComponent extends DrawableComponent<List<Component>> {
    default boolean append() {
        return true;
    }
}
