package me.ashydev.iframework.framework.types.registry;

public interface IRegistryKey<T> {
    T get();
    IRegistryKey<T> set(T value);

    @Override
    int hashCode();
}
