package net.bytemc.evelon.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytemc.evelon.repository.annotations.Row;

import java.lang.reflect.Field;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryHelper {

    public static String getFieldName(Field field) {
        if (field.isAnnotationPresent(Row.class)) {
            var row = field.getDeclaredAnnotation(Row.class);
            if (!row.name().isEmpty()) {
                return row.name();
            }
        }
        return field.getName();
    }

}
