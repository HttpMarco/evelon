package dev.httpmarco.evelon.common.filtering.common;

import dev.httpmarco.osgan.utils.types.NumberUtils;
import dev.httpmarco.evelon.common.filtering.Filter;

public abstract class AbstractNumberFilter<T> extends Filter<T, Number> {

    public AbstractNumberFilter(String id, Number value) {
        super(id, value);
    }

    @Override
    public boolean requirementCheck(Class<?> clazz) {
        return NumberUtils.isNumber(clazz);
    }
}
