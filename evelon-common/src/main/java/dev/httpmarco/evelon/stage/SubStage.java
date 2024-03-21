package dev.httpmarco.evelon.stage;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;

public interface SubStage extends Stage {

    void attachAffectedRows(Process<?> process, RepositoryObjectClass<?> clazz);
}
