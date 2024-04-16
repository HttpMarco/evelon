package dev.httpmarco.evelon.repository.external;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.lang.reflect.Field;
import java.util.List;

@Getter
@Accessors(fluent = true)
public final class RepositoryMapEntry extends RepositoryExternalEntry {

    private final RepositoryEntry keyEntry;
    private final RepositoryEntry valueEntry;

    public RepositoryMapEntry(String id, @NotNull Field field) {
        super(id, field.getType(), RepositoryEntryType.MAP);

        // todo read
        this.keyEntry = new RepositoryEntry(id + "_key", null, null);
        this.valueEntry = new RepositoryEntry(id + "_value", null, null);
    }

    @Contract(pure = true)
    @Override
    public @Unmodifiable List<RepositoryEntry> children() {
        return List.of();
    }
}