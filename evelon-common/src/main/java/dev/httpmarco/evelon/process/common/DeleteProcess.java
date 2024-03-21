package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;

public abstract class DeleteProcess extends Process {

    public DeleteProcess(String id, Repository<?> repository) {
        super(id, repository, false);
    }

    public abstract UpdateResponse pushDelete();

}
