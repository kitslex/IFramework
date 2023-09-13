package me.ashydev.iframework.framework.types;

public interface Modifier<I, O> {
    O modify(I input);
}
