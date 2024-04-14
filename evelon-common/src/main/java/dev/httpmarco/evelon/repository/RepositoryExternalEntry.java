package dev.httpmarco.evelon.repository;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class RepositoryExternalEntry extends RepositoryEntry  {

    private final List<RepositoryEntry> children = new ArrayList<>();

    public RepositoryExternalEntry(String id, Class<?> clazz, RepositoryEntryType type) {
        super(id, clazz, type);
    }
}
