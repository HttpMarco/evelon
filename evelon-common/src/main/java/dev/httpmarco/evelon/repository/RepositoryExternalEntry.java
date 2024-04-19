package dev.httpmarco.evelon.repository;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.LinkedList;
import java.util.List;

public abstract class RepositoryExternalEntry extends RepositoryEntry {

    private final List<RepositoryEntry> children = new LinkedList<>();

    public RepositoryExternalEntry(String id, Class<?> clazz, RepositoryExternalEntry type) {
        super(id, clazz, type);
    }

    public List<RepositoryEntry> primaries() {
        return this.children.stream().filter(it -> it.constants().has(RepositoryConstant.PRIMARY_KEY)).toList();
    }

    public void children(RepositoryEntry entry) {
        this.children.add(entry);
    }

    @Contract(pure = true)
    @Unmodifiable
    public List<RepositoryEntry> children() {
        return List.copyOf(this.children);
    }

    public void copyChildren(@NotNull RepositoryExternalEntry entry) {
        this.children.addAll(entry.children());
    }
}