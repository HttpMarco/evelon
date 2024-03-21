package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.repository.Repository;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

public abstract class ConstructProcess<T> extends Process {

    @Getter
    @Accessors(fluent = true)
    private final int limit;

    public ConstructProcess(String id, Repository<T> repository, int limit) {
        super(id, repository, true);
        this.limit = limit;
    }

    public abstract QueryResponse<List<T>> queryConstruct();

}
