package dev.httpmarco.evelon.repository.entries;

import dev.httpmarco.evelon.repository.RepositoryEntry;

import java.lang.reflect.Field;

public class RepositoryMapEntry extends RepositoryEntry {

    private RepositoryEntry keyEntry;
    private RepositoryEntry valueEntry;

    public RepositoryMapEntry(String id, Field field) {
        super(id, field.getType());

        // todo read
    }
}
