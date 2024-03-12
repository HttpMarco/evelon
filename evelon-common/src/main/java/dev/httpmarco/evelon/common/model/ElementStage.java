package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.repository.RepositoryField;

/**
 *
 * @param <T> Type of class
 * @param <E> Serialized Type
 * @param <B> Specific Builder Type
 */

public interface ElementStage<T, E, B extends Builder<B, ?, ?>> extends Stage<T, B> {

    T construct(Model<B> model, RepositoryField<?> clazz, B builder);

}
