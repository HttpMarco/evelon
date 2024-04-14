package dev.httpmarco.evelon.stages;

import dev.httpmarco.evelon.repository.RepositoryEntry;

import java.util.Set;

public interface SubStage<E extends RepositoryEntry> extends Stage {

    Set<RepositoryEntry> childrenEntries(E repositoryEntry);

}
