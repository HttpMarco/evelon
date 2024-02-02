package dev.httpmarco.evelon.common.filters.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.httpmarco.evelon.common.filters.Filter;
import dev.httpmarco.evelon.common.misc.Reflections;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class AbstractNumberFilter<T> implements Filter<T, Number> {

    private final String id;
    private final Number value;

    @Override
    public boolean requirementCheck(Class<?> clazz) {
        return Reflections.isNumber(clazz);
    }
}
