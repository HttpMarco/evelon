package dev.httpmarco.evelon.stages;

public interface Stage {

    default boolean isSubStage() {
        return this instanceof SubStage;
    }

    default SubStage asSubStage() {
        return (SubStage) this;
    }
}