package dev.httpmarco.evelon.repository.entries;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public final class RepositoryCollectionEntry extends RepositoryEntry {

    private final RepositoryEntry componentEntry;

    public RepositoryCollectionEntry(String id, @NotNull Field field) {
        super(id, field.getType());

        //todo
        this.componentEntry = RepositoryEntryType.scan(null).generation().generate(field.getName() + "_value", null, null);
    }
}
