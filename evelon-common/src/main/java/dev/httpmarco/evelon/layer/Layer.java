package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;
import dev.httpmarco.evelon.stages.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Accessors(fluent = true)
public abstract class Layer<Q> {

    // general auth binding id
    @Getter
    private final String id;

    // executor for every process
    @Getter
    private final ProcessRunner<Q> runner = generateRunner();

    // all stages for this layer mapped by their type
    private final Map<RepositoryEntryType, Stage> stages = new HashMap<>();

    public void overwrite(RepositoryEntryType type, Stage stage) {
        stages.put(type, stage);
    }

    public Stage stageOf(@NotNull RepositoryEntry entry) {
        return stages.get(entry.type());
    }

    public ProcessRunner<Q> generateRunner() {
        return new ProcessRunner<>(this);
    }
}