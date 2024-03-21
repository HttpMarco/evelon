package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryClass;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class ConstructProcess<T> extends Process {

    private final int limit;
    private final RepositoryClass<?> reconstructClass;

    public ConstructProcess(RepositoryClass<?> reconstructClass, String id, Repository<T> repository, int limit) {
        super(id, repository, true);
        this.limit = limit;
        this.reconstructClass = reconstructClass;
    }

    public abstract QueryResponse<List<T>> queryConstruct();

}
