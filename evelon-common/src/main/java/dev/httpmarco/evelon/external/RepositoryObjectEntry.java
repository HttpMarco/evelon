package dev.httpmarco.evelon.external;

import dev.httpmarco.evelon.*;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.Getter;

@Getter
public final class RepositoryObjectEntry extends RepositoryExternalEntry {

    public RepositoryObjectEntry(String id, Class<?> clazz, RepositoryExternalEntry parent) {
        super(id, clazz, parent);

        for (var field : Reflections.on(clazz).allFields()) {
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
            entry.constants().put(RepositoryConstant.PARAM_FIELD, field);

            if (field.isAnnotationPresent(PrimaryKey.class)) {
                entry.constants().add(RepositoryConstant.PRIMARY_KEY);
            }
            children(entry);
        }

        for (var child : children()) {
            if (!(child instanceof RepositoryExternalEntry)) {
                continue;
            }
            child.constants().put(RepositoryConstant.FOREIGN_REFERENCE, primaries());
        }
    }
}