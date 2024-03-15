package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.process.impl.ConstructProcess;
import dev.httpmarco.evelon.common.repository.RepositoryField;

/**
 *
 * @param <T> Type of class
 * @param <E> Serialized Type
 */

public interface ElementStage<T, E> extends Stage<T> {

    T construct(Model model, RepositoryField<?> clazz, ConstructProcess builder);

}
