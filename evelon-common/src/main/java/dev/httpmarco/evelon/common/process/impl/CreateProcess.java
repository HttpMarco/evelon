package dev.httpmarco.evelon.common.process.impl;

import dev.httpmarco.evelon.common.process.Build;
import dev.httpmarco.evelon.common.process.Process;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class CreateProcess<Q> extends Process<Q> {

    private final List<RepositoryField<?>> rows = new ArrayList<>();

    public CreateProcess(Build<Q> build) {
        super(build);
    }

    @Override
    public void run() {

        for (var row : rows) {
            // todo
        }
    }

    public CreateProcess<Q> withRow(RepositoryField<?> field) {
        this.rows.add(field);
        return this;
    }

    public CreateProcess<Q> withRows(RepositoryField<?>[] field) {
        this.rows.addAll(List.of(field));
        return this;
    }
}
