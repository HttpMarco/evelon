package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.repository.Repository;

public abstract class CreateProcess extends Process<CreateProcess> implements Process.UpdateProcess {

    public CreateProcess(String id, Repository<?> repository) {
        super(id, repository);
    }


    @Override
    public void run() {

    }
}
