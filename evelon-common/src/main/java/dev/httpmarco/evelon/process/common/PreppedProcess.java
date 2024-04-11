package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;

public class PreppedProcess<Q> implements Process<Q> {

    @Override
    public Q run(Repository<?> repository, RepositoryEntry entry) {
        if (entry.orderedType() == RepositoryEntryType.OBJECT) {

        }
        return null;
    }
}
