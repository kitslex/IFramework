package me.ashydev.iframework.framework.types.registry;

import it.unimi.dsi.fastutil.Hash;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import me.ashydev.iframework.framework.types.IPair;
import me.ashydev.iframework.framework.types.Pair;

import java.util.*;

public class Registry<K, S> implements IRegistry<K, S> {
    private final MapType mapType;
    private Map<K, S> registry;

    public Registry(MapType mapType) {
        this.mapType = mapType;
        setupMap();
    }

    public Registry() {
        this(MapType.NORMAL);
    }

    public Registry(Map<K, S> registry) {
        this.registry = registry;
        this.mapType = MapType.CUSTOM;
    }

    public Registry(int capacity) {
        this(new Object2ObjectOpenHashMap<>(capacity));
    }

    void setupMap() {
        switch (mapType) {
            case FAST:
                registry = new Object2ObjectOpenHashMap<>();
                break;
            case FAST_ACCESS:
                registry = new Object2ObjectOpenHashMap<>(Hash.DEFAULT_INITIAL_SIZE, Hash.FAST_LOAD_FACTOR);
                break;
            case TREE:
                registry = new TreeMap<>();
                break;
            default:
                registry = new HashMap<>();
                break;
        }
    }

    @Override
    public S get(K key) {
        return registry.get(key);
    }

    @Override
    public S getOrDefault(K key, S defaultValue) {
        return registry.getOrDefault(key, defaultValue);
    }

    @Override
    public Optional<S> getOptional(K key) {
        return Optional.ofNullable(get(key));
    }

    @Override
    public IRegistry<K, S> add(K key, S value) {
        registry.put(key, value);
        return this;
    }

    @Override
    public IRegistry<K, S> addAll(Collection<IPair<K, S>> collection) {
        collection.forEach(pair -> registry.put(pair.getFirst(), pair.getSecond()));
        return this;
    }

    @Override
    public IRegistry<K, S> addAll(IRegistry<K, S> registry) {
        return addAll(registry.getEntries());
    }

    @Override
    public IRegistry<K, S> remove(K key) {
        registry.remove(key);
        return this;
    }

    @Override
    public IRegistry<K, S> removeAll(Collection<K> collection) {
        collection.forEach(registry::remove);
        return this;
    }

    @Override
    public IRegistry<K, S> removeAll(IRegistry<K, S> registry) {
        return removeAll(registry.getKeys());
    }

    @Override
    public IRegistry<K, S> register(K key, S value) {
        registry.put(key, value);
        return this;
    }

    @Override
    public IRegistry<K, S> set(K key, S value) {
        return register(key, value);
    }

    @Override
    public IRegistry<K, S> registerAll(Collection<IPair<K, S>> collection) {
        return addAll(collection);
    }

    @Override
    public IRegistry<K, S> registerAll(IRegistry<K, S> registry) {
        return addAll(registry);
    }

    @Override
    public IRegistry<K, S> setAll(Collection<IPair<K, S>> collection) {
        return registerAll(collection);
    }

    @Override
    public IRegistry<K, S> setAll(IRegistry<K, S> registry) {
        return registerAll(registry);
    }

    @Override
    public Collection<IPair<K, S>> getEntries() {
        Collection<IPair<K, S>> collection = new ArrayList<>();
        registry.forEach((key, value) -> collection.add(new Pair<>(key, value)));
        return collection;
    }

    @Override
    public Collection<K> getKeys() {
        return registry.keySet();
    }

    @Override
    public Collection<S> getValues() {
        return registry.values();
    }

    public Map<K, S> getRegistry() {
        return registry;
    }

    public void setRegistry(Map<K, S> registry) {
        this.registry = registry;
    }

    public enum MapType {
        NORMAL, FAST, FAST_ACCESS, TREE, CUSTOM
    }
}
