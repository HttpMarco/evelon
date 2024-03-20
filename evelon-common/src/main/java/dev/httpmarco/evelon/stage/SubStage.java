package dev.httpmarco.evelon.stage;

import dev.httpmarco.evelon.process.common.InitializeProcess;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;

public interface SubStage extends Stage {

    void initialize(RepositoryObjectClass<?> clazz, InitializeProcess process);

}
