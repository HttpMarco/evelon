package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.stages.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public class RepositoryEntry {

    private final String id;
    // the original class of the entry
    private final Class<?> clazz;
    // current type of field
    private final RepositoryEntryType type;
    // the constants of the entry
    private final Map<RepositoryConstant<?>, ?> constants = new ConcurrentHashMap<>();

    public Stage stage(Layer layer) {
        return layer.stageOf(this);
    }
}