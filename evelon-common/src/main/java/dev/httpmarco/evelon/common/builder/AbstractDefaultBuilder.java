package dev.httpmarco.evelon.common.builder;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public abstract class AbstractDefaultBuilder<T extends Builder<T>> implements Builder<T> {

    private String id;

    private final Model<T> model;
    private final List<RepositoryField> queryFields = new ArrayList<>();

    private final T parent;
    private final List<T> children = new ArrayList<>();

    public @Nullable T parent() {
        return this.parent;
    }
}
