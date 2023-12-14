package net.bytemc.evelon.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
