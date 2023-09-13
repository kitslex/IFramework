package me.ashydev.iframework.framework.item;

import me.ashydev.iframework.framework.item.component.types.ArrayComp;
import me.ashydev.iframework.framework.item.component.ItemComponent;

public interface CustomItem {
    ArrayComp<ItemComponent> getComponents();
}
