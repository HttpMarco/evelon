package dev.httpmarco.evelon.common.misc;

import java.lang.reflect.Field;
import java.util.List;

public class Reflections {

    public static final List<Class<?>> CONSTANT = List.of(String.class, Integer.class, Boolean.class, Short.class, Float.class, Byte.class, Double.class, Long.class);

    public static boolean isNumber(Class<?> clazz) {
        return Number.class.isAssignableFrom(clazz);
    }

    public static Object getFieldValue(String id, Object value) {
        try {
            return getFieldValue(value.getClass().getDeclaredField(id), value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getFieldValue(Field field, Object value) {
        try {
            return field.get(value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}