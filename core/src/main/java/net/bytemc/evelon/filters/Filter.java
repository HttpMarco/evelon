package net.bytemc.evelon.filters;

import net.bytemc.evelon.repository.Repository;

public interface Filter<T, R> {

    String getId();

    T filter(Repository<?> repository, R requiredType);

    default boolean requirementCheck(Class<?> clazz) {
        return true;
    }
}
