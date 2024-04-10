package dev.httpmarco.evelon.stage.stages;

import dev.httpmarco.evelon.repository.RepositoryEntryType;
import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import dev.httpmarco.evelon.stage.AbstractStage;
import dev.httpmarco.evelon.stage.SubStage;

public final class RepositoryObjectEntryStage extends AbstractStage<RepositoryObjectEntry> implements SubStage<RepositoryObjectEntry> {

    public RepositoryObjectEntryStage(RepositoryEntryType entryType) {
        super(entryType);
    }
}