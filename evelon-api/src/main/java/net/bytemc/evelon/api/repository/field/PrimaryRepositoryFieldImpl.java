package net.bytemc.evelon.api.repository.field;

import net.bytemc.evelon.api.repository.RepositoryClass;

import java.lang.reflect.Field;

public final class PrimaryRepositoryFieldImpl extends RepositoryFieldImpl {

    public PrimaryRepositoryFieldImpl(Field field, RepositoryClass parentClass) {
        super(field, parentClass);
    }
}
