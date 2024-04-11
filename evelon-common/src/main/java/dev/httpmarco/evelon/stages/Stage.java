package dev.httpmarco.evelon.stages;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;

public interface Stage {

    void initialize(RepositoryObjectEntry entry, Layer layer);

    default boolean isSubStage() {
        return this instanceof SubStage;
    }

    default SubStage<?> asSubStage() {
        return (SubStage<?>) this;
    }
}