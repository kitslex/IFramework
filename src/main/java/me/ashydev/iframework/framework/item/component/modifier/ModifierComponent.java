package me.ashydev.iframework.framework.item.component.modifier;

import me.ashydev.iframework.framework.item.component.ItemComponent;
import me.ashydev.iframework.framework.types.Modifier;

public interface ModifierComponent<I, O> extends ItemComponent, Modifier<I, O> {

    default O apply(I input) {
        return modify(input);
    }
}
