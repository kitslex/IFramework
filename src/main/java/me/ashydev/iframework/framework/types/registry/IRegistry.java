package me.ashydev.iframework.framework.types.registry;

import me.ashydev.iframework.framework.types.IPair;

import java.util.Collection;
import java.util.Optional;

public interface IRegistry<K, S> {

    S get(K key);

    S getOrDefault(K key, S defaultValue);

    Optional<S> getOptional(K key);

    IRegistry<K, S> add(K key, S value);

    IRegistry<K, S> addAll(Collection<IPair<K, S>> collection);

    IRegistry<K, S> addAll(IRegistry<K, S> registry);


    IRegistry<K, S> remove(K key);

    IRegistry<K, S> removeAll(Collection<K> collection);

    IRegistry<K, S> removeAll(IRegistry<K, S> registry);


    IRegistry<K, S> register(K key, S value);

    IRegistry<K, S> set(K key, S value);

    IRegistry<K, S> registerAll(Collection<IPair<K, S>> collection);

    IRegistry<K, S> registerAll(IRegistry<K, S> registry);

    IRegistry<K, S> setAll(Collection<IPair<K, S>> collection);

    IRegistry<K, S> setAll(IRegistry<K, S> registry);

    Collection<IPair<K, S>> getEntries();

    Collection<K> getKeys();

    Collection<S> getValues();
}
