package dev.httpmarco.evelon.common.filtering;

import dev.httpmarco.evelon.common.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@Accessors(fluent = true)
@Getter
public abstract class Filter<T, R> {

    private String id;

    abstract T filter(Repository<?> repository, R requiredType);

    boolean requirementCheck(Class<?> clazz) {
        return true;
    }
}
