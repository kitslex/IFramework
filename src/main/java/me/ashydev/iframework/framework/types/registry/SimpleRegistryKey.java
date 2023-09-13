package me.ashydev.iframework.framework.types.registry;

public class SimpleRegistryKey<T> implements IRegistryKey<T> {
    private T value;

    public SimpleRegistryKey(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public IRegistryKey<T> set(T value) {
        this.value = value;
        return this;
    }

    public static <T> SimpleRegistryKey<T> of(T value) {
        return new SimpleRegistryKey<T>(value);
    }

    @Override
    public String toString() {
        return "SimpleRegistryKey{" +
                "value=" + value +
                '}';
    }
}
