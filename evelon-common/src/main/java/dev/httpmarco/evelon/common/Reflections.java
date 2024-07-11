package dev.httpmarco.evelon.common;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

@UtilityClass
public class Reflections {

    @SneakyThrows
    public static void modify(Object parent, @NotNull Field field, Object value) {
        field.setAccessible(true);
        field.set(parent, value);
    }

    @Contract(pure = true)
    @SneakyThrows
    public static Object value(@NotNull Field field, Object object) {
        field.setAccessible(true);
        return field.get(object);
    }

    @Contract(pure = true)
    @SneakyThrows
    public static @NotNull Field field(@NotNull Class<?> parent, String field) {
        return parent.getDeclaredField(field);
    }
}
