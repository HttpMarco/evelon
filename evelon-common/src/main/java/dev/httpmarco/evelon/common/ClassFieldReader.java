package dev.httpmarco.evelon.common;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@UtilityClass
public class ClassFieldReader {

    public Field[] fields(Class<?> clazz) {
        return clazz.getDeclaredFields();
    }

    public Set<Field> allFields(Class<?> clazz) {
        var fields = new HashSet<>(Arrays.asList(clazz.getDeclaredFields()));
        var scannedPathClass = clazz;

        while (scannedPathClass.getSuperclass() != null) {
            scannedPathClass = scannedPathClass.getSuperclass();
            fields.addAll(Arrays.asList(scannedPathClass.getDeclaredFields()));
        }
        return fields;
    }

}
