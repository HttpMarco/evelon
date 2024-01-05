package net.bytemc.evelon.api.repository.field;

import net.bytemc.evelon.api.repository.RepositoryClass;
import net.bytemc.evelon.api.repository.RepositoryField;

import java.lang.reflect.Field;

public class RepositoryFieldImpl implements RepositoryField {

    private final String id;
    private final RepositoryClass parentClass;

    public RepositoryFieldImpl(Field field, RepositoryClass parentClass) {
        this.id = field.getName();
        this.parentClass = parentClass;
    }

    @Override
    public String id() {
        return this.id;
    }

    @Override
    public RepositoryClass parentClass() {
        return this.parentClass;
    }
}
