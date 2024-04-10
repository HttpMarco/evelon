package dev.httpmarco.evelon.stage.stages;

import dev.httpmarco.evelon.repository.RepositoryEntryType;
import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import dev.httpmarco.evelon.stage.Stage;
import dev.httpmarco.evelon.stage.SubStage;

public class RepositoryObjectEntryStage implements SubStage<RepositoryObjectEntry> {

    @Override
    public RepositoryEntryType entryType() {
        return null;
    }
}
