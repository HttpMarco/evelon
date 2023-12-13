package net.bytemc.evelon.model;

public interface ElementStage<E> extends Stage {

    E serializeElement(Object element);

}
