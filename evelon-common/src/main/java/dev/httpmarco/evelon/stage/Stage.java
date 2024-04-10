package dev.httpmarco.evelon.stage;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;

/**
 * Head of all stages
 * @param <E> Type of stage entry
 */
public interface Stage<E extends RepositoryEntry> {

    RepositoryEntryType entryType();

}
