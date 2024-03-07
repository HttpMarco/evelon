package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

public interface SubStage<T, B extends Builder<B, ?>> extends Stage<T, B> {

    void initialize(String stageId, Model<B> model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, B queries);

    void create(String stageId, Model<B> model, RepositoryField<T> ownField, RepositoryObjectClass<?> clazz, B queries);

}
