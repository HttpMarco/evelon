package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

public interface SubStage<T, B extends Builder<B, ?, ?>> extends Stage<T, B> {

    /**
     * Initialize the repository values
     * @param repository repository
     * @param stageId parent collect id
     * @param model model
     * @param ownField self field in parent class
     * @param clazz parent class
     * @param builder builder
     */
    void initialize(Repository<?> repository, String stageId, Model<B> model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, B builder);

    /**
     * Save a value in the database
     * @param value of element
     * @param stageId parent collect id
     * @param model model
     * @param ownField self field in parent class
     * @param clazz parent class
     * @param queries builder
     */
    void create(T value, String stageId, Model<B> model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, B queries);

    T construct(Model<B> model, RepositoryClass<?> clazz, B builder);

    @SuppressWarnings("unchecked")
    @Deprecated
    default void createMapping(Object value, String stageId, Model<B> model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, B builder) {
        this.create((T) value, stageId, model, ownField, clazz, builder);
    }
}
