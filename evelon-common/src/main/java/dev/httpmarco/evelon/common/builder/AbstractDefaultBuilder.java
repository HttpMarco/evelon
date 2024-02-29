package dev.httpmarco.evelon.common.builder;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.field.ForeignLinkingRepositoryFieldImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
public abstract class AbstractDefaultBuilder<T extends Builder<T>> implements Builder<T> {

    private String id;

    private final Model<T> model;
    private final List<RepositoryField> queryFields = new ArrayList<>();
    private final List<ForeignLinkingRepositoryFieldImpl> foreignLinking = new ArrayList<>();

    private final T parent;
    private final List<T> children = new ArrayList<>();

    public @Nullable T parent() {
        return this.parent;
    }

    @Override
    public Builder<T> foreignLinkings(ForeignLinkingRepositoryFieldImpl... field) {
        foreignLinking.addAll(Arrays.stream(field).toList());
        return this;
    }
}
