package net.bytemc.evelon.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytemc.evelon.misc.EvelonReflections;

@Getter
@AllArgsConstructor
public abstract class AbstractNumberFilter<T> implements Filter<T, Number> {

    private final String id;
    private final Number value;

    @Override
    public boolean requirementCheck(Class<?> clazz) {
        return EvelonReflections.isNumber(clazz);
    }
}
