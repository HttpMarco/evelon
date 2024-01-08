package dev.httpmarco.evelon.common.filters.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.httpmarco.evelon.common.filters.Filter;

@Getter
@AllArgsConstructor
public abstract class AbstractTextFilter<T> implements Filter<T, String> {

    private final String id;
    private final String value;

    @Override
    public boolean requirementCheck(Class<?> clazz) {
        return clazz.equals(String.class);
    }
}
