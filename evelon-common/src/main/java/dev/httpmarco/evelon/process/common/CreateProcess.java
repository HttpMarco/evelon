package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;

public abstract class CreateProcess extends Process {

    public CreateProcess(String id, Repository<?> repository) {
        super(id, repository);
    }

    public abstract UpdateResponse pushCreate(Object value);

}