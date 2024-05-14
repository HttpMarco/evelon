package dev.httpmarco.evelon.constant;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ConstantPool {

    public final Map<Constant<?>, Object> constants = new ConcurrentHashMap<>();

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

    public <T> T  constantOrCreate(Constant<T> constant, T defaultValue) {
        if(has(constant)) {
            return constant(constant);
        }
        this.constants.put(constant, defaultValue);
        return defaultValue;
    }

    @SuppressWarnings("unchecked")
    public <T> T constant(Constant<T> constant) {
        return (T) constants.get(constant);
    }

    public void cloneConstants(@NotNull ConstantPool pool) {
        this.constants.putAll(pool.constants);
    }

    public void cloneConstants(@NotNull ConstantPool pool, Constant<?> constant) {
        this.constants.put(constant, pool.constant(constant));
    }
}