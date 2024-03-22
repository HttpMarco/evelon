package dev.httpmarco.evelon.process.common;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.repository.RepositoryClass;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class ConstructProcess<T> extends Process<T> {

    private final int limit;
    private final RepositoryClass<?> reconstructClass;

    public ConstructProcess(RepositoryClass<?> reconstructClass, String id, Query<T> query, int limit) {
        super(id, query, true);
        this.limit = limit;
        this.reconstructClass = reconstructClass;
    }

    public abstract QueryResponse<List<T>> queryConstruct(Layer layer);

}
