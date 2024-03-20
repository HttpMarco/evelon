package dev.httpmarco.evelon.stage;

import dev.httpmarco.osgan.utils.Utils;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.function.Predicate;

@AllArgsConstructor
public enum Type {


    PARAMETER(it -> it.isPrimitive() || Utils.JAVA_ELEMENTS.contains(it)),

    OBJECT(it -> {
        // we only accept fields that are of a type that is a stage type
        // we are not allowed that field parameters
        return true;//Arrays.stream(it.getDeclaredFields()).noneMatch(s -> typeOf(s.getType()).name().equals("UNKNOWN")
                ///todo
               // !(s.getClass().getPackageName().startsWith("java.") && Arrays.stream(s.getClass().getDeclaredFields())
                //        .allMatch(f -> f.getClass().getPackageName().startsWith("java."))));

    });

    // memory need a long time to load this -> we cache it
    public static final List<Type> TYPES = Arrays.stream(values()).toList();
    private final Predicate<Class<?>> type;

    public static Type typeOf(Class<?> type) {
        return TYPES.stream().filter(it -> it.type.test(type)).findFirst().orElse(null);
    }
}
