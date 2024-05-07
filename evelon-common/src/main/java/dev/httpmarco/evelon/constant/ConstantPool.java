package dev.httpmarco.evelon.constant;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ConstantPool {

    private final Map<Constant<?>, Object> constants = new ConcurrentHashMap<>();

    public void option(Constant<Void> constant) {
        constants.put(constant, true);
    }

    public <T> T put(Constant<T> constant, T value) {
        constants.put(constant, value);
        return value;
    }

    public boolean has(Constant<?> constant) {
        return constants.containsKey(constant);
    }

    @SuppressWarnings("unchecked")
    public <T> T constant(Constant<T> constant) {
        return (T) constants.get(constant);
    }

    public void cloneConstants(@NotNull ConstantPool pool) {
        this.constants.putAll(pool.constants);
    }
}
