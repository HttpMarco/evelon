package dev.httpmarco.evelon.stage;

import dev.httpmarco.evelon.repository.RepositoryEntry;

/**
 * Represent an entry with minimum two parameters in on data set.
 * @param <E> type of entry
 */
public interface MultiStage<E extends RepositoryEntry> extends Stage<E> {
}
