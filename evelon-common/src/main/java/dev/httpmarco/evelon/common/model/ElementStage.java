package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;

/**
 *
 * @param <T> Type of class
 * @param <E> Serialized Type
 * @param <B> Specific Builder Type
 */

public interface ElementStage<T, E, B extends Builder<?, ?>> extends Stage<T, B> {

    E serializeElement(T element);

}
