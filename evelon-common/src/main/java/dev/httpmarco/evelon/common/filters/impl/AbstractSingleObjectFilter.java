package dev.httpmarco.evelon.common.filters.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.httpmarco.evelon.common.filters.Filter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class AbstractSingleObjectFilter<T> implements Filter<T, Object> {

    private final String id;
    private final Object value;

}
