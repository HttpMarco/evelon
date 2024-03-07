package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

import java.lang.reflect.Field;

public final class PrimaryRepositoryFieldImpl<T> extends RepositoryFieldImpl<T> {

    public PrimaryRepositoryFieldImpl(Field field, RepositoryObjectClass<?> parentClass) {
        super(field, parentClass);
    }

    public PrimaryRepositoryFieldImpl(RepositoryClass<T> clazz, String id, RepositoryObjectClass<?> parentClass) {
        super(clazz, id, parentClass);
    }
}
