package dev.httpmarco.evelon.sql.parent.exception;

import org.jetbrains.annotations.NotNull;

public final class UnknownSqlTypeException extends RuntimeException {

    public UnknownSqlTypeException(@NotNull Class<?> clazz) {
        super("Unknown sql type for class: " + clazz.getName());
    }
}
