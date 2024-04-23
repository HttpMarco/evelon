package dev.httpmarco.evelon.external;

import dev.httpmarco.evelon.RepositoryEntry;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.osgan.reflections.Reflections;
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

    public RepositoryMapEntry(String id, @NotNull Field field, RepositoryExternalEntry parent) {
        super(id, field.getType(), parent);

        this.keyEntry = new RepositoryEntry(id + "_key", Reflections.on(field).generic(0), this);
        this.valueEntry = new RepositoryEntry(id + "_value", Reflections.on(field).generic(1), this);
    }

    @Contract(pure = true)
    @Override
    public @Unmodifiable List<RepositoryEntry> children() {
        return List.of();
    }
}