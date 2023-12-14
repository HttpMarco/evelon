package net.bytemc.evelon.exceptions;

public class RequiredFieldTypeNotPresentException extends NullPointerException {

    public RequiredFieldTypeNotPresentException(Class<?> requiredType, Class<?> actualType) {
        super("Required type " + requiredType.getSimpleName() + " is not present in " + actualType.getSimpleName() + "!");
    }
}
