package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

import java.lang.reflect.Field;

public class ForeignLinkingRepositoryFieldImpl<T> extends RepositoryFieldImpl<T> {

    public ForeignLinkingRepositoryFieldImpl(Field field, RepositoryObjectClass<?> parentClass) {
        super(field, parentClass);
    }

    public ForeignLinkingRepositoryFieldImpl(Class<T> fieldType, String id, RepositoryObjectClass<?> parentClass) {
        super(fieldType, id, parentClass);
    }
}
