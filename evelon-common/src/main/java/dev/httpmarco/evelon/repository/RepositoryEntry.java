package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.stages.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

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
    private final RepositoryConstantPool constants = new RepositoryConstantPool();

    public Stage stage(@NotNull Layer<?> layer) {
        return layer.stageOf(this);
    }
}