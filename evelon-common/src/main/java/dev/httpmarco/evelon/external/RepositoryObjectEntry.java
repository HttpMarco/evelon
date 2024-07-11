package dev.httpmarco.evelon.external;

import dev.httpmarco.evelon.*;
import dev.httpmarco.evelon.common.ClassFieldReader;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

@Getter
public final class RepositoryObjectEntry extends RepositoryExternalEntry {

    public RepositoryObjectEntry(String id, Class<?> clazz, RepositoryExternalEntry parent) {
        super(id, clazz, parent);

        if (!clazz.isAnnotationPresent(Entity.class) || !clazz.getDeclaredAnnotation(Entity.class).ignoreSuperClass()) {
            for (var field : ClassFieldReader.allFields(clazz)) {
                applyField(field);
            }
        } else {
            for (var field : ClassFieldReader.fields(clazz)) {
                applyField(field);
            }
        }

        for (var child : children()) {
            if (!(child instanceof RepositoryExternalEntry)) {
                continue;
            }
            child.constants().put(RepositoryConstant.FOREIGN_REFERENCE, primaries());
        }
    }

    private void applyField(@NotNull Field field) {
        // evelon does not allow static fields, only object parameters
        if (Modifier.isStatic(field.getModifiers())) {
            return;
        }

        var fieldId = field.getName();

        if (field.isAnnotationPresent(Row.class)) {
            var row = field.getDeclaredAnnotation(Row.class);

            if (row.ignore()) {
                return;
            }

            if (!row.id().isEmpty()) {
                fieldId = row.id();
            }
        }

        var entry = RepositoryEntryFinder.find(field, fieldId, this);
        entry.constants().put(RepositoryConstant.PARAM_FIELD, field);

        if (field.isAnnotationPresent(PrimaryKey.class)) {
            entry.constants().option(RepositoryConstant.PRIMARY_KEY);
        }
        children(entry);
    }
}