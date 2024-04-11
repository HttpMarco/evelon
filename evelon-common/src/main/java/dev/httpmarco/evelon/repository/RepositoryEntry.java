package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.stages.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

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
    private final Map<RepositoryConstant<?>, Object> constants = new ConcurrentHashMap<>();

    public Stage stage(@NotNull Layer layer) {
        return layer.stageOf(this);
    }

    public <T> void constant(RepositoryConstant<T> constant, T value) {
        constants.put(constant, value);
    }
}