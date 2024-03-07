package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;

/**
 * @param <T> Class type
 * @param <B> Specific builder type
 */
public interface Stage<T, B extends Builder<B, ?>> {

    boolean isElement(Class<?> type);

    default boolean isSubStage() {
        return this instanceof SubStage<T, B>;
    }

    default SubStage<T, B> asSubStage() {
        return (SubStage<T, B>) this;
    }

    default boolean isSubElementStage() {
        return this instanceof ElementStage<T, ?, B>;
    }

    default ElementStage<T, ?, B> asElementStage() {
        return (ElementStage<T, ?, B>) this;
    }
}