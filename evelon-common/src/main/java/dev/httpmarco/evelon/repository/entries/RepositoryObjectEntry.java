package dev.httpmarco.evelon.repository.entries;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;

import java.util.HashSet;
import java.util.Set;

public final class RepositoryObjectEntry extends RepositoryEntry {

    private final Set<RepositoryEntry> entries = new HashSet<>();

    public RepositoryObjectEntry(String id, Class<?> clazz) {
        super(id, clazz);

        // todo read all superclass fields with osgan
        for (var field : clazz.getDeclaredFields()) {
            this.entries.add(RepositoryEntryType.scan(field.getType()).generation().generate(field.getName(), field.getType(), field));
        }
    }
}