package dev.httpmarco.evelon.repository.entries;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

@Getter
@Accessors(fluent = true)
public final class RepositoryMapEntry extends RepositoryEntry {

    private final RepositoryEntry keyEntry;
    private final RepositoryEntry valueEntry;

    public RepositoryMapEntry(String id, @NotNull Field field) {
        super(id, field.getType(), RepositoryEntryType.MAP);

        // todo read
        this.keyEntry = new RepositoryEntry(id + "_key", null, null);
        this.valueEntry = new RepositoryEntry(id + "_value", null, null);
    }
}