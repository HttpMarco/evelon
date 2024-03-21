package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;

public abstract class InitializeProcess extends Process {

    public InitializeProcess(String id, Repository<?> repository) {
        super(id, repository);
    }

    public abstract void pushInitialize();

}