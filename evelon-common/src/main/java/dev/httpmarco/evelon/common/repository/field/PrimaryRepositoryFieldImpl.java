package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

import java.lang.reflect.Field;

public final class PrimaryRepositoryFieldImpl<T> extends ForeignLinkingRepositoryFieldImpl<T> {

    public PrimaryRepositoryFieldImpl(Field field, RepositoryObjectClass<?> parentClass) {
        super(field, parentClass);
    }

    public PrimaryRepositoryFieldImpl(Class<T> fieldType, String id, RepositoryObjectClass<?> parentClass) {
        super(fieldType, id, parentClass);
    }
}
