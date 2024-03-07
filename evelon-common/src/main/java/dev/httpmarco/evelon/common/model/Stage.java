package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;

/**
 *
 * @param <T> Class type
 * @param <B> Specific builder type
 */
public interface Stage<T, B extends Builder<?, ?>> {

    boolean isElement(Class<?> type);

    default SubStage<T, B> asSubStage() {
        return (SubStage<T, B>) this;
    }

    default ElementStage<T, ?, B> asElementStage() {
        return (ElementStage<T, ?, B>) this;
    }
}