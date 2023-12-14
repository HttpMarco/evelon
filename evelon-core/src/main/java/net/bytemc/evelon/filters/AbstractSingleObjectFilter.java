package net.bytemc.evelon.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractSingleObjectFilter<T> implements Filter<T, Object> {

    private final String id;
    private final Object value;

}