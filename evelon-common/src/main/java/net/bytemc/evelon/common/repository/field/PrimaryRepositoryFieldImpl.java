package net.bytemc.evelon.common.repository.field;

import net.bytemc.evelon.common.repository.RepositoryClass;

import java.lang.reflect.Field;

public final class PrimaryRepositoryFieldImpl extends RepositoryFieldImpl {

    public PrimaryRepositoryFieldImpl(Field field, RepositoryClass parentClass) {
        super(field, parentClass);
    }
}
