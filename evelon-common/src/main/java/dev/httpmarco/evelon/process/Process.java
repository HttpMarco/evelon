package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryEntry;

public interface Process<Q> {

    Q run(Repository<?> repository, RepositoryEntry entry);

}
