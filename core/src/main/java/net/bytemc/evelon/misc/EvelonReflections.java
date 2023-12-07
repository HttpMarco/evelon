package net.bytemc.evelon.misc;

public class EvelonReflections {

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
