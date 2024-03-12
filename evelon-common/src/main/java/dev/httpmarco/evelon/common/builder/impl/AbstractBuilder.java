package dev.httpmarco.evelon.common.builder.impl;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.builder.BuildProcess;
import dev.httpmarco.evelon.common.model.Model;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Accessors(fluent = true)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor
public abstract class AbstractBuilder<B extends Builder<B, E, D>, M extends Model<B>, E, D> implements Builder<B, E, D> {

    // general parameters
    private final String id;
    private final M model;
    private @Nullable B parent;
    private final List<B> children = new ArrayList<>();
    private final BuildProcess type;
    private final E executor;

    private final Map<String, Object> values = new HashMap<>();

    @Override
    public void appendValue(String key, Object value) {
        this.values.put(key, value);

    }

    @Override
    public List<Object> values() {
        return this.values.values().stream().toList();
    }

    @Override
    public Map<String, Object> valuesMap() {
        return this.values;
    }
}
