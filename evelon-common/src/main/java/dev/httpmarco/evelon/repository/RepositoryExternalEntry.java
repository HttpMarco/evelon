package dev.httpmarco.evelon.repository;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.LinkedList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class RepositoryExternalEntry extends RepositoryEntry  {

    private final List<RepositoryEntry> children = new LinkedList<>();

    public RepositoryExternalEntry(String id, Class<?> clazz, RepositoryEntryType type) {
        super(id, clazz, type);
    }

    public List<RepositoryEntry> primaries() {
        return this.children.stream().filter(it -> it.constants().has(RepositoryConstant.PRIMARY_KEY)).toList();
    }
}