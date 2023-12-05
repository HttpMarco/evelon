package net.bytemc.evelon.repository;

import net.bytemc.evelon.annotations.RowName;

import java.lang.reflect.Field;

final class RepositoryField {

    private final String name;
    private final Field field;

    public RepositoryField(Field field) {
        if (field.isAnnotationPresent(RowName.class)) {
            this.name = field.getDeclaredAnnotation(RowName.class).name();
        } else {
            this.name = field.getName();
        }
        this.field = field;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> type() {
        return this.field.getType();
    }
}
