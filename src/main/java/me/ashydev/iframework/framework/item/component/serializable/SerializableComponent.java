package me.ashydev.iframework.framework.item.component.serializable;

import me.ashydev.iframework.framework.item.component.ItemComponent;
import me.ashydev.iframework.framework.types.serializable.ISerializable;

public interface SerializableComponent<S> extends ItemComponent, ISerializable<S> { }
