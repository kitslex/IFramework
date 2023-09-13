package me.ashydev.iframework.basic.item;

import me.ashydev.iframework.framework.item.AbstractItem;
import me.ashydev.iframework.framework.item.component.ItemComponent;
import me.ashydev.iframework.framework.item.component.MetaComponent;
import me.ashydev.iframework.framework.item.component.types.ArrayComp;

import java.util.Optional;

public class CommonItem extends AbstractItem {
    public CommonItem(MetaComponent meta, ArrayComp<ItemComponent> components) {
        super(components.with(meta));
    }

    public Optional<MetaComponent> getMeta() {
        return getComponents()
                .getItemsOfType(MetaComponent.class)
                .findFirst();
    }
}
