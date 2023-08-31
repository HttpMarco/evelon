package net.bytemc.evelon.exception;

import java.lang.reflect.Field;

public final class NumberNotPresentException extends RuntimeException {

    public NumberNotPresentException(Class<?> type, String id) {
        super("The field '" + id + "' in the class '" + type.getSimpleName() + "' is not a number type.");
    }
}
