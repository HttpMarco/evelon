package dev.httpmarco.evelon.common.filters;

import dev.httpmarco.evelon.common.repository.Repository;

public interface Filter<T, R> {

    String getId();

    T filter(Repository<?> repository, R requiredType);

    default boolean requirementCheck(Class<?> clazz) {
        return true;
    }
}
