package me.ashydev.iframework.framework.types;

public interface IConvertible<I, O> {
    O convert(I input);
}
