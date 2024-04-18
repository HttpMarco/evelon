package dev.httpmarco.evelon.repository.external;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

public final class RepositoryCollectionEntry extends RepositoryExternalEntry {

    private final RepositoryEntry componentEntry;

    public RepositoryCollectionEntry(String id, @NotNull Field field) {
        super(id, field.getType(), RepositoryEntryType.COLLECTION);

        this.componentEntry = RepositoryEntryType.find(field.getName() + "_value", Evelon.generics(field)[0]);
    }
}
