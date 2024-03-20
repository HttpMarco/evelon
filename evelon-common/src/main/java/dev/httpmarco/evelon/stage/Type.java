package dev.httpmarco.evelon.stage;

import dev.httpmarco.osgan.utils.Utils;
import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@AllArgsConstructor
public enum Type {


    PARAMETER(it -> it.isPrimitive() || Utils.JAVA_ELEMENTS.contains(it)),

    OBJECT(it -> {
        // we only accept fields that are of a type that is a stage type
        // we are not allowed that field parameters
        return Arrays.stream(it.getDeclaredFields()).allMatch(s -> typeOf(s.getType()) != null &&
                (s.getClass().getPackageName().startsWith("java.") && Arrays.stream(s.getClass().getDeclaredFields())
                        .allMatch(f -> f.getClass().getPackageName().startsWith("java."))));
    }),

    UNKNOWN(it -> true);

    // memory need a long time to load this -> we cache it
    private static final Set<Type> TYPES = new HashSet<>(List.of(Type.values()));

    private final Predicate<Class<?>> type;

    public static Type typeOf(Class<?> type) {
        return TYPES.stream().filter(it -> it.type.test(type)).findFirst().orElse(null);
    }
}
