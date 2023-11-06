package net.bytemc.evelon.exception.field;

public final class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException(Class<?> clazz, String id) {
        super("The field '" + id + "' was not found in class '" + clazz.getName() + "'");
    }
}
