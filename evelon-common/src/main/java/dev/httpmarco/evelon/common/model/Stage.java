package dev.httpmarco.evelon.common.model;

public interface Stage<R> {

    boolean isElement(Class<?> type);

    default SubStage<R> asSubStage() {
        return (SubStage<R>) this;
    }
}