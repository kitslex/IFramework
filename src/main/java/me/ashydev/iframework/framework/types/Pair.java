package me.ashydev.iframework.framework.types;

public class Pair<A, B> implements IPair<A, B> {
    private A a;
    private B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public Pair() {
        this(null, null);
    }

    @Override
    public A getFirst() {
        return a;
    }

    @Override
    public B getSecond() {
        return b;
    }

    @Override
    public void setFirst(A value) {
        a = value;
    }

    @Override
    public void setSecond(B value) {
        b = value;
    }
}
