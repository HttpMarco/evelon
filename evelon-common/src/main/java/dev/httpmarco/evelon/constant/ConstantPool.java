package dev.httpmarco.evelon.constant;

import dev.httpmarco.evelon.RepositoryConstant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ConstantPool {

    private final Map<RepositoryConstant<?>, Object> constants = new ConcurrentHashMap<>();

    public void option(RepositoryConstant<Void> constant) {
        constants.put(constant, true);
    }

    public <T> T put(RepositoryConstant<T> constant, T value) {
        constants.put(constant, value);
        return value;
    }

    public boolean has(RepositoryConstant<?> constant) {
        return constants.containsKey(constant);
    }

    @SuppressWarnings("unchecked")
    public <T> T constant(RepositoryConstant<T> constant) {
        return (T) constants.get(constant);
    }

}
