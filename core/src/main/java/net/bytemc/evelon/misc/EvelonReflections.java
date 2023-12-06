package net.bytemc.evelon.misc;

import net.bytemc.evelon.repository.RepositoryField;

public class EvelonReflections {

    public static boolean isNumber (RepositoryField field) {
        return Number.class.isAssignableFrom(field.type());
    }

    public static Object getFieldValue(String id, Object value) {
        try {
            return value.getClass().getDeclaredField(id).get(value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
