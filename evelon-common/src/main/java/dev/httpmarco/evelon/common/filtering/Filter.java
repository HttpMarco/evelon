package dev.httpmarco.evelon.common.filtering;

import dev.httpmarco.evelon.common.repository.Repository;

public interface Filter<T, R> {

    String id();

    T filter(Repository<?> repository, R requiredType);

    default boolean requirementCheck(Class<?> clazz) {
        return true;
    }
}
