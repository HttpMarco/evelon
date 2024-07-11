package dev.httpmarco.evelon.common;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

@UtilityClass
public class GenericReader {

    public static Class<?> @NotNull [] types(@NotNull Field field){
        var genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType parameterizedType) {
            return Arrays.stream(parameterizedType.getActualTypeArguments()).map(type -> (Class<?>) type).toArray(value -> new Class<?>[value]);
        } else {
            throw new UnsupportedOperationException("Cannot read generic from field: " + field.getName());
        }
    }

    public static Class<?> type(Field field, int index) {
        return types(field)[0];
    }
}
