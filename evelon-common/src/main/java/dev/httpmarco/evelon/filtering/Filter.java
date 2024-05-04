package dev.httpmarco.evelon.filtering;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Accessors(fluent = true)
@Getter
public abstract class Filter<T, R> {

    private String id;
    private R value;

    public abstract T filter();

    public boolean requirementCheck(Class<?> clazz) {
        return true;
    }
}
