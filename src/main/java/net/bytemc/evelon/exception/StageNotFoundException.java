package net.bytemc.evelon.exception;

public final class StageNotFoundException extends NullPointerException {

    public StageNotFoundException(Class<?> searchedStageClass) {
        super("No stage found for class: " + searchedStageClass.getSimpleName());
    }
}
