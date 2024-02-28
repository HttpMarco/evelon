package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;

public interface ElementStage<E, B extends Builder, T> extends Stage<B> {

    E serializeElement(Object element);

    T classBuilderType(Class<?> element);

}
