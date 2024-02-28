package dev.httpmarco.evelon.sql.parent.exception;

public final class UnknownSqlTypeException extends RuntimeException {

    public UnknownSqlTypeException(Class<?> clazz) {
        super("Unknown sql type for class: " + clazz.getName());
    }
}
