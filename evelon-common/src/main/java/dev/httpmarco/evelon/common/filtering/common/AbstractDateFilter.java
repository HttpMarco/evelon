package dev.httpmarco.evelon.common.filtering.common;

import dev.httpmarco.evelon.common.filtering.Filter;

import java.util.Date;

public abstract class AbstractDateFilter<T> extends Filter<T, Date> {

    public AbstractDateFilter(String id, Date value) {
        super(id, value);
    }

    @Override
    public boolean requirementCheck(Class<?> clazz) {
        // todo
        return super.requirementCheck(clazz);
    }
}
