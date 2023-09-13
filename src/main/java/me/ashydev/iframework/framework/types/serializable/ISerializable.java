package me.ashydev.iframework.framework.types.serializable;

public interface ISerializable<T> {
    T serialize();
    void deserialize(T t);
}
