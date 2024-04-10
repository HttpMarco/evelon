package dev.httpmarco.evelon.repository.entries;

import dev.httpmarco.evelon.annotations.Row;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryType;

import java.util.HashSet;
import java.util.Set;

public final class RepositoryObjectEntry extends RepositoryEntry {

    private final Set<RepositoryEntry> entries = new HashSet<>();

    public RepositoryObjectEntry(String id, Class<?> clazz) {
        super(id, clazz, RepositoryEntryType.OBJECT);

        // todo read all superclass fields with osgan
        for (var field : clazz.getDeclaredFields()) {
            var fieldId = field.getName();

            if (field.isAnnotationPresent(Row.class)) {
                var row = field.getDeclaredAnnotation(Row.class);

                if (row.ignore()) {
                    continue;
                }

                if (!row.id().isEmpty()) {
                    fieldId = row.id();
                }
            }
            this.entries.add(RepositoryEntryType.generate(fieldId, field));
        }
    }
}