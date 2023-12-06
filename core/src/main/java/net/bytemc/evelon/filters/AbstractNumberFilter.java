package net.bytemc.evelon.filters;

import net.bytemc.evelon.misc.EvelonReflections;

public abstract class AbstractNumberFilter<T> implements Filter<T, Number> {

    private final String id;
    private final Number value;

    public AbstractNumberFilter(String id, Number value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public String getId() {
        return id;
    }

    public Number getValue() {
        return value;
    }

    @Override
    public boolean requirementCheck(Class<?> clazz) {
        return EvelonReflections.isNumber(clazz);
    }
}
