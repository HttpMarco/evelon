package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

/**
 * @param <R> response of initialize phase (String for query languages)
 */
public interface SubStage<R> extends Stage<R> {

    void initialize(String stageId, Model<?> model, RepositoryObjectClass<?> clazz, R queries);

    @SuppressWarnings("unchecked")
    default void initializeWithMapping(String stageId, Model<?> model, RepositoryObjectClass<?> clazz, Object queries) {
        this.initialize(stageId, model, clazz, (R) queries);
    }

}
