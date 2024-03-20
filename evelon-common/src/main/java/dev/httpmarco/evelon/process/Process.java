package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryClass;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Accessors(fluent = true)
public abstract class Process implements Runnable {

    private String id;
    private final ProcessMeta meta;
    private final Set<RepositoryClass<?>> affectedRows = new LinkedHashSet<>();

    public Process(String id, Repository<?> repository) {
        this.id = id;
        this.meta = new ProcessMeta(repository);
    }
}
