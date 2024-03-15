package dev.httpmarco.evelon.common.process.impl;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.process.Build;
import dev.httpmarco.evelon.common.process.Process;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class InitializeProcess<Q> extends Process<Q> {

    private final List<RepositoryField<?>> rows = new ArrayList<>();
    private final Q queryContext;

    public InitializeProcess(Build<Q> build, Q queryContext) {
        super(build);
        this.queryContext = queryContext;
    }

    @Override
    public void run() {
        this.preQuery();
        for (int i = 0; i < rows.size(); i++) {
            var row = rows.get(i);

            if (applyRow(build().model(), row)) {
                this.preElement();
                this.appendQuery(row);

                if (i < rows.size() - 1) {
                    this.betweenElement();
                }

                this.postElement();
            }
        }

        this.postQuery();
        execute();
    }

    public abstract void appendQuery(RepositoryField<?> field);

    public void postElement() {
    }

    public void betweenElement() {
    }

    public void preElement() {
    }

    public InitializeProcess<Q> withRow(RepositoryField<?> field) {
        if (applyRow(build().model(), field)) {
            this.rows.add(field);
        }
        return this;
    }

    public InitializeProcess<Q> withRows(RepositoryField<?>[] field) {
        for (var repositoryField : field) {
            withRow(repositoryField);
        }
        return this;
    }

    public boolean applyRow(Model model, RepositoryField<?> field) {
        return true;
    }
}
