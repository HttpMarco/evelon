package dev.httpmarco.evelon.common.model;

public interface ElementStage<E, B, T> extends Stage<B> {

    E serializeElement(Object element);

    T classBuilderType(Class<?> element);

}
