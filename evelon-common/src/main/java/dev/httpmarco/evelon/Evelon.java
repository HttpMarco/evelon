package dev.httpmarco.evelon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

public final class Evelon {

    // main logger of evelon
    public static final Logger LOGGER = LoggerFactory.getLogger(Evelon.class);

    // todo replace with osgan-reflections
    public static Class<?>[] generics(Field field) {
        var genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType parameterizedType) {
            return Arrays.stream(parameterizedType.getActualTypeArguments()).map(type -> (Class<?>) type).toArray(value -> new Class<?>[value]);
        } else {
            throw new UnsupportedOperationException("Cannot read generic from field: " + field.getName());
        }
    }
}
