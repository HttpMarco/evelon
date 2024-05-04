package dev.httpmarco.evelon.sql.parent.types;

import dev.httpmarco.evelon.RepositoryEntry;
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
    private Predicate<RepositoryEntry> predicate;

    public static @NotNull Type of(String type, Class<?>... compatibleClass) {
        return new Type(type, it -> Arrays.asList(compatibleClass).contains(it.clazz()));
    }

    public static @NotNull Type of(String type, Predicate<RepositoryEntry> predicate) {
        return new Type(type, predicate);
    }

    @Override
    public String toString() {
        return type;
    }
}
