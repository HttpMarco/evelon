package dev.httpmarco.evelon.common.exceptions;

public final class LayerNotInClassloaderException extends RuntimeException {

    public LayerNotInClassloaderException(Class<?> clazz) {
        super("Cannot find the layer in the classloader. Please check the layer name and the classloader. Layer: " + clazz.getSimpleName());
    }
}
