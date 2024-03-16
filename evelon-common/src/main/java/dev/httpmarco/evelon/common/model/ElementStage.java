package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.process.impl.ConstructProcess;
import dev.httpmarco.evelon.common.repository.RepositoryField;

/**
 *
 * @param <T> Type of class
 */

public interface ElementStage<T> extends Stage<T> {

    T construct(Model model, RepositoryField<?> clazz, ConstructProcess builder);

}
