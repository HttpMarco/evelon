package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;
import dev.httpmarco.evelon.stages.Stage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Accessors(fluent = true)
public abstract class Layer {

    // general auth binding id
    @Getter
    private final String id;

    // executor for every process
    @Getter
    private final ProcessRunner runner = generateRunner();

    // all stages for this layer mapped by their type
    private final Map<RepositoryEntryType, Stage> stages = new HashMap<>();

    public Layer appendStage(RepositoryEntryType type, Stage stage) {
        stages.put(type, stage);
        return this;
    }

    public Stage stageOf(RepositoryEntry entry) {
        return stages.get(entry.type());
    }

    public ProcessRunner generateRunner() {
        return new ProcessRunner(this);
    }
}
