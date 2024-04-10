package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.layer.AbstractLayer;
import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.stage.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class RepositoryEntry {

    private final String id;
    // the original class of the entry
    private final Class<?> clazz;
    private final RepositoryEntryType orderedType;
    // the constants of the entry
    private final Map<RepositoryConstant<?>, ?> constants = new ConcurrentHashMap<>();
    // save the reference of the entry stage
    private final Map<Layer<?>, Stage<? extends RepositoryEntry>> stage = new HashMap<>();

    public void appendLayer(Layer<?> abstractLayer) {

    }

}
