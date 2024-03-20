package dev.httpmarco.evelon.stage;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.common.InitializeProcess;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;

public interface SubStage extends Stage {

    void initialize(Layer layer, RepositoryObjectClass<?> clazz, InitializeProcess process);

}
