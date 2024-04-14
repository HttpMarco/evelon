package dev.httpmarco.evelon.stages;

import dev.httpmarco.evelon.repository.RepositoryEntry;

public interface SingleStage<T> extends Stage {

    T transform(RepositoryEntry entry);

}
