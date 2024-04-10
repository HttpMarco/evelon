package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.stage.Stage;
import dev.httpmarco.evelon.stage.stages.RepositoryObjectEntryStage;

public class PreppedProcess<Q> implements Process<Q> {

    @Override
    public Q run(Stage<?> stage, Repository<?> repository, RepositoryEntry entry) {
        System.err.println("jojo");
        if(stage instanceof RepositoryObjectEntryStage) {
            System.err.println("jojo2");
        }
        return null;
    }
}
