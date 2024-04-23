package dev.httpmarco.evelon;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class RepositoryExternalEntry extends RepositoryEntry {

    private final List<RepositoryEntry> children = new LinkedList<>();

    public RepositoryExternalEntry(String id, Class<?> clazz, RepositoryExternalEntry type) {
        super(id, clazz, type);
    }

    public List<RepositoryEntry> primaries() {
        return this.children.stream().filter(it -> it.hasConstant(RepositoryConstant.PRIMARY_KEY)).toList();
    }

    public void children(RepositoryEntry entry) {
        if (entry instanceof RepositoryExternalEntry) {
            this.children.add(entry);
        } else {
            this.children.add(0, entry);
        }
    }

    public Collection<Object> readValues(Object parent) {
        return List.of(parent);
    }
}