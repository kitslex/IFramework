package me.ashydev.iframework.framework.item.component.types;

import me.ashydev.iframework.framework.item.component.DrawableComponent;
import me.ashydev.iframework.framework.item.component.ItemComponent;
import org.bukkit.entity.Item;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class ArrayComp<T extends ItemComponent> extends ArrayList<T> {
    public ArrayComp(int initialCapacity) {
        super(initialCapacity);
    }

    public ArrayComp() {}

    public ArrayComp(@NotNull Collection<? extends T> c) {
        super(c);
    }

    public ArrayComp(@NotNull T... components) {
        super(List.of(components));
    }

    @SuppressWarnings("unchecked")
    public <S extends ItemComponent> Stream<S> getItemsOfType(Class<S> type) {
        return stream().filter(type::isInstance).map(item -> (S) item);
    }

    @SuppressWarnings("unchecked")
    public <S extends ItemComponent> ArrayComp<S> getItemsOfType(Class<S> type, boolean arrayComp) {
        return new ArrayComp<>(stream().filter(type::isInstance).map(item -> (S) item).toList());
    }

    @SuppressWarnings("unchecked")
    public <S> Stream<DrawableComponent<S>> getDrawables(Class<? extends S> type) {
        return stream().filter(DrawableComponent.class::isInstance).map(item -> (DrawableComponent<S>) item);
    }

    @SuppressWarnings("unchecked")
    public <S> ArrayComp<DrawableComponent<S>> getDrawables(Class<? extends S> type, boolean arrayComp) {
        return new ArrayComp<>(stream().filter(DrawableComponent.class::isInstance).map(item -> (DrawableComponent<S>) item).toList());
    }

    public Stream<DrawableComponent<?>> getDrawables() {
        return stream().filter(DrawableComponent.class::isInstance).map(item -> (DrawableComponent<?>) item);
    }

    public ArrayComp<DrawableComponent<?>> getDrawables(boolean arrayComp) {
        return new ArrayComp<>(stream().filter(DrawableComponent.class::isInstance).map(item -> (DrawableComponent<?>) item).toList());
    }

    public <S extends T> ArrayComp<T> with(ArrayComp<S> components) {
        addAll(components);
        return this;
    }
    @SafeVarargs
    public final ArrayComp<T> with(T... components) {
        addAll(List.of(components));
        return this;
    }
    
    public static <S extends ItemComponent> ArrayComp<S> with(ArrayComp<S> components, boolean staticMethod) {
        return new ArrayComp<>(components);
    }
    
    @SafeVarargs
    public static <S extends ItemComponent> ArrayComp<S> with(boolean staticMethod, S... components) {
        return new ArrayComp<>(List.of(components));
    }

    public static <T extends ItemComponent> ArrayComp<T> of(T... components) {
        return new ArrayComp<>(List.of(components));
    }
}
