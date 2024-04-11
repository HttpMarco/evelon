package dev.httpmarco.evelon.stages.subs;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import dev.httpmarco.evelon.stages.SubStage;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Set;

public abstract class AbstractObjectSubStage<T> implements SubStage<RepositoryObjectEntry> {

    @Override
    public void initialize(RepositoryObjectEntry entry, Layer layer) {
        System.err.println(bindItem(childrenEntries(entry).stream().filter(it -> {
            var stage = layer.stageOf(it);
            if (stage.isSubStage()) {
                stage.asSubStage().initialize((RepositoryObjectEntry) it, layer);
                return false;
            }
            return true;
        }).map(this::transform).toList()));
    }

    @Override
    public Set<RepositoryEntry> childrenEntries(@NotNull RepositoryObjectEntry entry) {
        return entry.getEntries();
    }

    public abstract T transform(RepositoryEntry entry);

    public abstract T bindItem(List<T> transformedElements);

}
