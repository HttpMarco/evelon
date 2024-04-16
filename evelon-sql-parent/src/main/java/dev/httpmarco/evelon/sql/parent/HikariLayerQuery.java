package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.query.Query;
import dev.httpmarco.evelon.sql.parent.process.HikariCreateProcess;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HikariLayerQuery<T> implements Query<T> {

    private final Repository<T> repository;
    private ProcessRunner<String> runner;

    @Override
    public void create(T value) {
        runner.apply(new HikariCreateProcess(), repository);
    }
}
