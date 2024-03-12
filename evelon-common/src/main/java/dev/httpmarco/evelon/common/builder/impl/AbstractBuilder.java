package dev.httpmarco.evelon.common.builder.impl;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.builder.BuilderType;
import dev.httpmarco.evelon.common.model.Model;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Accessors(fluent = true)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public abstract class AbstractBuilder<B extends Builder<B, A, D>, M extends Model<B>, A, D> implements Builder<B, A, D> {

    // general parameters
    private final String id;
    private final M model;

    private @Nullable B parent;
    private final List<B> children = new ArrayList<>();

    private final List<Object> values = new ArrayList<>();

    private final BuilderType type;

    @Override
    public void appendValue(Object value) {
        this.values.add(value);
    }
}
