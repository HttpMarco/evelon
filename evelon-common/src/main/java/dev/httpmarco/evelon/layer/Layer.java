package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.filtering.FilterHandler;
import dev.httpmarco.evelon.layer.connection.Connection;
import dev.httpmarco.evelon.process.ProcessReference;
import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.query.QueryMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent = true)
public abstract class Layer<Q extends ProcessReference<? extends Connection<?, ?>>> {

    // general auth binding id
    private final String id;

    // executor for every process
    private final ProcessRunner<Q> runner = generateRunner();

    private final FilterHandler<?, ?> filterHandler;

    protected abstract ProcessRunner<Q> generateRunner();

    public abstract <T> QueryMethod<T> queryMethod(Repository<?> repository);

}