package dev.httpmarco.evelon.common.builder;

import dev.httpmarco.evelon.common.repository.RepositoryField;

/**
 * represent a builder of elements in a data query/update
 */
public interface Builder<T> {

    /**
     * Create a new builder for sub elements, list or maps
     *
     * @return new sub builder
     */
    Builder<T> subBuilder(String id);

    /**
     * Add a value in the current storage
     * @param repositoryField value how added
     */
    Builder<T> withField(RepositoryField repositoryField);

}
