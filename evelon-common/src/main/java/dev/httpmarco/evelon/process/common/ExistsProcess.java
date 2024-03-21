package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.repository.Repository;

public abstract class ExistsProcess extends Process {

    public ExistsProcess(String id, Repository<?> repository) {
        super(id, repository, false);
    }

    public abstract QueryResponse<Boolean> queryExists();

}
