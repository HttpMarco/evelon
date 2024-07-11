package dev.httpmarco.evelon.common;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public final class JavaUtils {

    public static final List<Class<?>> JAVA_ELEMENTS = List.of(String.class, Integer.class, Boolean.class, Short.class, Float.class, Byte.class, Double.class, Long.class);

}
