package dev.httpmarco.evelon.common.builder.impl;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.builder.BuilderType;
import dev.httpmarco.evelon.common.model.Model;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter(AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class AbstractBuilder<T extends Builder<?, A>, M extends Model<?>, A> implements Builder<T, A> {

    // general parameters
    private final String id;
    private final M model;

    private final BuilderType type;

}
