package dev.httpmarco.evelon.common.filtering.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.httpmarco.evelon.common.filtering.Filter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class AbstractSingleObjectFilter<T> implements Filter<T, Object> {

    private final String id;

}
