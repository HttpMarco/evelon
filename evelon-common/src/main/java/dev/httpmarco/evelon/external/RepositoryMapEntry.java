package dev.httpmarco.evelon.external;

import dev.httpmarco.evelon.RepositoryEntry;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.common.GenericReader;
import dev.httpmarco.evelon.common.Pair;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.lang.reflect.Field;
import java.util.*;

@Getter
@Accessors(fluent = true)
public final class RepositoryMapEntry extends RepositoryExternalEntry {

    private final RepositoryEntry keyEntry;
    private final RepositoryEntry valueEntry;

    public RepositoryMapEntry(String id, @NotNull Field field, RepositoryExternalEntry parent) {
        super(id, field.getType(), parent);

        var genericData = GenericReader.types(field);
        this.keyEntry = new RepositoryEntry(field.getName() + "_key", genericData[0], this);
        this.valueEntry = new RepositoryEntry(field.getName() + "_value", genericData[1], this);
    }

    @Contract(pure = true)
    @Override
    public @Unmodifiable @NotNull List<RepositoryEntry> children() {
        return List.of(keyEntry, valueEntry);
    }

    @Contract(pure = true)
    @Override
    public @NotNull Collection<Object> readValues(Object parent) {
        var elements = new ArrayList<>();
        ((Map<?, ?>) parent).forEach((o, o2) -> elements.add(new Pair<>(o, o2)));
        return elements;
    }
}