package net.bytemc.evelon.exception;

public final class StageNotSupportedException extends RuntimeException {

    public StageNotSupportedException(Class<?> clazz) {
        super("Stage for class " + clazz.getSimpleName() + " not supported.");
    }
}
