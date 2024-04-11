package dev.httpmarco.evelon.stages.subs;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import dev.httpmarco.evelon.stages.SubStage;

import java.util.List;
import java.util.Set;

public abstract class ObjectSubStage<T> implements SubStage<RepositoryObjectEntry> {

    @Override
    public void initialize(RepositoryObjectEntry entry) {
        System.err.println(bindItem(childrenEntries(entry).stream().map(this::transform).toList()));
    }

    public abstract T transform(RepositoryEntry entry);

    public abstract T bindItem(List<T> transformedElements);

    @Override
    public Set<RepositoryEntry> childrenEntries(RepositoryObjectEntry entry) {
        return entry.getEntries();
    }
}
