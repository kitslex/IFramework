package me.ashydev.iframework.framework.item;

import me.ashydev.iframework.framework.item.component.ItemComponent;
import me.ashydev.iframework.framework.item.component.MetaComponent;
import me.ashydev.iframework.framework.item.component.types.ArrayComp;

import java.util.Collection;
import java.util.Optional;

public abstract class AbstractItem implements CustomItem {
    private final ArrayComp<ItemComponent> components = new ArrayComp<>();

    public AbstractItem(Collection<? extends ItemComponent> components) {
        this.components.addAll(components);
    }

    public AbstractItem() { }

    @Override
    public ArrayComp<ItemComponent> getComponents() {
        return components;
    }
}
