package dev.httpmarco.evelon.repository.external;

import dev.httpmarco.evelon.repository.Row;
import dev.httpmarco.evelon.repository.RepositoryEntryType;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import lombok.Getter;

@Getter
public final class RepositoryObjectEntry extends RepositoryExternalEntry {

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
            children().add(RepositoryEntryType.find(fieldId, field));
        }
    }
}