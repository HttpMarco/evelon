package dev.httpmarco.evelon.sql.parent.types;

import dev.httpmarco.evelon.RepositoryEntry;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Predicate;

public final class TypeModel extends Type {

    private final Function<Class<?>, String> result;

    public TypeModel(Function<Class<?>, String> result, Predicate<RepositoryEntry> predicate) {
        super(null, predicate);
        this.result = result;
    }

    @Contract("_, _ -> new")
    public static @NotNull Type of(Function<Class<?>, String> result, Predicate<RepositoryEntry> predicate) {
        return new TypeModel(result, predicate);
    }

    @Contract("_ -> new")
    public @NotNull Type model(Class<?> clazz) {
        return new Type(result.apply(clazz), predicate());
    }
}
