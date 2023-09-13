package me.ashydev.iframework.framework.types;

public interface IContainer<S> {
    S get();

    void set(S value);
}
