package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.process.impl.ConstructProcess;
import dev.httpmarco.evelon.common.process.impl.CreateProcess;
import dev.httpmarco.evelon.common.process.impl.InitializeProcess;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

public interface SubStage<T> extends Stage<T> {

    /**
     * Initialize the repository values
     *
     * @param repository repository
     * @param stageId    parent collect id
     * @param model      model
     * @param ownField   self field in parent class
     * @param clazz      parent class
     * @param builder    builder
     */
    void initialize(Repository<?> repository, String stageId, Model model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, InitializeProcess builder);

    /**
     * Save a value in the database
     *
     * @param value    of element
     * @param stageId  parent collect id
     * @param model    model
     * @param ownField self field in parent class
     * @param clazz    parent class
     * @param queries  builder
     */
    void create(T value, String stageId, Model model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, CreateProcess queries);

    T construct(Model model, RepositoryClass<?> clazz, ConstructProcess builder);

    @SuppressWarnings("unchecked")
    @Deprecated
    default void createMapping(Object value, String stageId, Model model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, CreateProcess builder) {
        this.create((T) value, stageId, model, ownField, clazz, builder);
    }
}
