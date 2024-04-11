package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.stage.Stage;

public interface Process<Q> {

    Q run(Stage<?> stage, Repository<?> repository, RepositoryEntry entry);

}
