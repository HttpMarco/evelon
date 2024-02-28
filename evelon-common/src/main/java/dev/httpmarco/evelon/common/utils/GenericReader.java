package dev.httpmarco.evelon.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

// todo add to osgan api
public class GenericReader {

    public static Class<?>[] readGenericFromClass(Field field) {
        var genericType = field.getGenericType();
        if(genericType instanceof ParameterizedType parameterizedType) {
            return Arrays.stream(parameterizedType.getActualTypeArguments()).map(type -> (Class<?>) type).toArray(value -> new Class<?>[value]);
        } else{
            throw new UnsupportedOperationException("Cannot read generic from field: " + field.getName());
        }
    }
}
