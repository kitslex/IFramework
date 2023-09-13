package me.ashydev.iframework.framework.types;

public interface Identifiable<T> extends Nameable<T> {

    T getIdentifier();

    default T getId() {
        return getIdentifier();
    }
}
