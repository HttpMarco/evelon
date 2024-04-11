package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter(AccessLevel.PROTECTED)
@Accessors(fluent = true)
@RequiredArgsConstructor
public class RepositoryEntry {

    private final String id;
    // the original class of the entry
    private final Class<?> clazz;
    // current ordered type of the entry
    private final RepositoryEntryType orderedType;
    // the constants of the entry
    private final Map<RepositoryConstant<?>, ?> constants = new ConcurrentHashMap<>();
    // save the reference of the entry stage
    private final Map<Layer<?>, Stage<? extends RepositoryEntry>> stage = new HashMap<>();

    /**
     * Get the stage of a current layer
     * @param layer the layer
     * @return the stage of layer
     */
    public Stage<? extends RepositoryEntry> stage(Layer<?> layer) {
        return this.stage.get(layer);
    }
}