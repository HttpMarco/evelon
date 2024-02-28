package dev.httpmarco.evelon.common.model;

import dev.httpmarco.evelon.common.builder.Builder;

public interface Stage<R extends Builder> {

    boolean isElement(Class<?> type);

    default SubStage<R> asSubStage() {
        return (SubStage<R>) this;
    }

    default ElementStage<?, R, ?> asElementStage() {
        return (ElementStage<?, R, ?>) this;
    }
}