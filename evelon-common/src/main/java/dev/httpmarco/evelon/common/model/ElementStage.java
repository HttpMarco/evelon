package dev.httpmarco.evelon.common.model;

public interface ElementStage<E> extends Stage {

    E serializeElement(Object element);

}
