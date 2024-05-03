package dev.httpmarco.evelon.sql.parent.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Predicate;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class Type {

    private String type;
    private Predicate<Class<?>> predicate;

    public static @NotNull Type of(String type, Class<?>... compatibleClass) {
        return new Type(type, aClass -> Arrays.asList(compatibleClass).contains(aClass));
    }

    @Override
    public String toString() {
        return type;
    }
}
