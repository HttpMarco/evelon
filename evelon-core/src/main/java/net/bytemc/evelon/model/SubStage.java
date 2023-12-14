package net.bytemc.evelon.model;

import net.bytemc.evelon.repository.RepositoryClass;

/**
 *
 * @param <R> responsement of initialize phase
 */
public interface SubStage<R> extends Stage {

    R initialize(String stageId, RepositoryClass<?> clazz);

}
