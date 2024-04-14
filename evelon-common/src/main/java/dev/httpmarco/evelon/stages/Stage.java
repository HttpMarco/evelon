package dev.httpmarco.evelon.stages;

public interface Stage {

    default boolean isSubStage() {
        return this instanceof SubStage;
    }

    default boolean isSingleStage() {
        return this instanceof SingleStage;
    }

    default SubStage asSubStage() {
        return (SubStage) this;
    }

    default SingleStage<?> asSingleStage() {
        return (SingleStage<?>) this;
    }
}