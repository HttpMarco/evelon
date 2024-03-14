package dev.httpmarco.evelon.common.filtering.common;

import dev.httpmarco.evelon.common.filtering.Filter;

public abstract class AbstractTextFilter<T> extends Filter<T, String> {

    public AbstractTextFilter(String id, String value) {
        super(id, value);
    }

    @Override
    public boolean requirementCheck(Class<?> clazz) {
        return clazz.equals(String.class);
    }
}
