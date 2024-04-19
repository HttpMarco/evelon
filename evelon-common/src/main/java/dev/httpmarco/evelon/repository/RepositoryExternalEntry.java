package dev.httpmarco.evelon.repository;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public abstract class RepositoryExternalEntry extends RepositoryEntry {

    @Getter
    @Accessors(fluent = true)
    private final List<RepositoryEntry> children = new LinkedList<>();

    public RepositoryExternalEntry(String id, Class<?> clazz, RepositoryExternalEntry type) {
        super(id, clazz, type);
    }

    public List<RepositoryEntry> primaries() {
        return this.children.stream().filter(it -> it.constants().has(RepositoryConstant.PRIMARY_KEY)).toList();
    }

    public void children(RepositoryEntry entry) {
        if (entry instanceof RepositoryExternalEntry) {
            this.children.add(entry);
        } else {
            this.children.add(0, entry);
        }
    }

    public void copyChildren(@NotNull RepositoryExternalEntry entry) {
        this.children.addAll(entry.children());
    }
}