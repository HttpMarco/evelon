package net.bytemc.evelon.misc;

import java.util.List;

public class EvelonReflections {

    public static final List<Class<?>> CONSTANT = List.of(String.class, Integer.class, Boolean.class, Short.class, Float.class, Byte.class, Double.class, Long.class);

    public static boolean isNumber(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz);
    }

    public static Object getFieldValue(String id, Object value) {
        try {
            return value.getClass().getDeclaredField(id).get(value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
