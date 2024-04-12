package dev.httpmarco.evelon.repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class RepositoryConstantPool {

    private final Map<RepositoryConstant<?>, Object> constants = new ConcurrentHashMap<>();

    public <T> void put(RepositoryConstant<T> constant, T value) {
        constants.put(constant, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(RepositoryConstant<T> constant) {
        return (T) constants.get(constant);
    }
}
