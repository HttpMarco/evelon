package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.process.common.InitializeProcess;
import dev.httpmarco.evelon.repository.Repository;

public interface Layer {

    void initialize(Repository<?> repository, InitializeProcess process);

}
