package dev.httpmarco.evelon.repository.external;

import dev.httpmarco.evelon.repository.*;
import lombok.Getter;

@Getter
public final class RepositoryObjectEntry extends RepositoryExternalEntry {

    public RepositoryObjectEntry(String id, Class<?> clazz, RepositoryExternalEntry parent) {
        super(id, clazz, parent);

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

            var entry = RepositoryEntryFinder.find(field, fieldId, this);

            if (field.isAnnotationPresent(PrimaryKey.class)) {
                entry.constants().add(RepositoryConstant.PRIMARY_KEY);
            }
            children().add(entry);
        }

        for (var child : children()) {
            if (!(child instanceof RepositoryExternalEntry externalEntry)) {
                continue;
            }

            for (var primary : primaries()) {
                child.constants().put(RepositoryConstant.FOREIGN_REFERENCE, primary);
            }
        }
    }
}