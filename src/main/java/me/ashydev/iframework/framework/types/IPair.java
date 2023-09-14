package me.ashydev.iframework.framework.types;

public interface IPair<A, B> {
    A getFirst();

    void setFirst(A value);

    B getSecond();

    void setSecond(B value);

    default A getA() {
        return getFirst();
    }

    default void setA(A value) {
        setFirst(value);
    }

    default B getB() {
        return getSecond();
    }

    default void setB(B value) {
        setSecond(value);
    }

    default A getKey() {
        return getFirst();
    }

    default void setKey(A value) {
        setFirst(value);
    }

    default B getValue() {
        return getSecond();
    }

    default void setValue(B value) {
        setSecond(value);
    }

    default A getLeft() {
        return getFirst();
    }

    default void setLeft(A value) {
        setFirst(value);
    }

    default B getRight() {
        return getSecond();
    }

    default void setRight(B value) {
        setSecond(value);
    }
}
