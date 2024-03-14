package dev.httpmarco.evelon.common.filtering.common;

import dev.httpmarco.osgan.utils.types.NumberUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import dev.httpmarco.evelon.common.filtering.Filter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class AbstractNumberFilter<T> implements Filter<T, Number> {

    private final String id;
    private final Number value;

    @Override
    public boolean requirementCheck(Class<?> clazz) {
        return NumberUtils.isNumber(clazz);
    }
}
