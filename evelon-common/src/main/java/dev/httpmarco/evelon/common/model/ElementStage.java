package dev.httpmarco.evelon.common.model;

public interface ElementStage<E, B> extends Stage<B> {

    E serializeElement(Object element);

}
