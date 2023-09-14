package me.ashydev.iframework.framework.item;

import me.ashydev.iframework.framework.item.component.ItemComponent;
import me.ashydev.iframework.framework.item.component.types.ArrayComp;

public interface CustomItem {
    ArrayComp<ItemComponent> getComponents();
}
