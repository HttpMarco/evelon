package dev.httpmarco.evelon.stage;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class AbstractStage<E extends RepositoryEntry> implements Stage<E> {

    private RepositoryEntryType entryType;

}
