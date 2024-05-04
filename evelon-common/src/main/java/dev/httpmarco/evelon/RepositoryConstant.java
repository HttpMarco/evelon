package dev.httpmarco.evelon;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Function;

public record RepositoryConstant<T>(String id) {

    // repository constants for own field if is present
    public static final RepositoryConstant<Field> PARAM_FIELD = constant("PARAM_FIELD");

    public static final RepositoryConstant<List<RepositoryEntry>> FOREIGN_REFERENCE = constant("FOREIGN_REFERENCE");

    public static final RepositoryConstant<Void> PRIMARY_KEY = constant("PRIMARY_KEY");

    public static final RepositoryConstant<Function<Object, Object>> VALUE_RENDERING = constant("VALUE_RENDERING");

    @Contract("_ -> new")
    private static <T> @NotNull RepositoryConstant<T> constant(String id) {
        return new RepositoryConstant<>(id);
    }
}
