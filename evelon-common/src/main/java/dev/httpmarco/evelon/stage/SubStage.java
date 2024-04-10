package dev.httpmarco.evelon.stage;

import dev.httpmarco.evelon.repository.RepositoryEntry;

/**
 * Represent a data set in a separate data set.
 * (Maps, Collection, binding objects)
 * @param <E>
 */
public interface SubStage<E extends RepositoryEntry> extends Stage<E> {
}
