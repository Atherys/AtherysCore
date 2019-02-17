package com.atherys.core.db.cache;

import com.atherys.core.db.Identifiable;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SimpleCache<T extends Identifiable<ID>, ID extends Serializable> implements Cache<T,ID> {

    private HashMap<ID,T> cache = new HashMap<>();

    public SimpleCache() {
    }

    @Override
    public void set(ID id, T cacheable) {
        cache.put(id, cacheable);
    }

    @Override
    public void add(T cacheable) {
        cache.put(cacheable.getId(), cacheable);
    }

    @Override
    public void addAll(Collection<T> cacheables) {
        cacheables.forEach(this::add);
    }

    @Override
    public void remove(T cacheable) {
        cache.remove(cacheable.getId());
    }

    @Override
    public void removeById(ID id) {
        cache.remove(id);
    }

    @Override
    public void removeAll(Collection<T> cacheables) {
        cacheables.forEach(this::remove);
    }

    @Override
    public void removeIf(Predicate<T> condition) {
        cache.values().parallelStream().filter(condition).forEach(this::remove);
    }

    @Override
    public Optional<T> getById(ID id) {
        T cacheable = cache.get(id);

        if (cacheable == null) {
            return Optional.empty();
        }

        return Optional.of(cacheable);
    }

    @Override
    public Collection<T> findAll(Predicate<T> query) {
        return cache.values().parallelStream().filter(query).collect(Collectors.toSet());
    }

    @Override
    public Optional<T> findOne(Predicate<T> query) {
        return cache.values().parallelStream().filter(query).findAny();
    }

    @Override
    public Collection<T> getAll() {
        return cache.values();
    }
}
