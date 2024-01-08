package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.repository.RepositoryClass;

/**
 *
 * @param <R> responsement of initialize phase
 */
public interface SubStage<R> extends Stage {

    R initialize(String stageId, RepositoryClass<?> clazz);

}
