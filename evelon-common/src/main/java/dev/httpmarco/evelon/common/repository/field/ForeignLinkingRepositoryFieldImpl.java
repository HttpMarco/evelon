package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

import java.lang.reflect.Field;

public class ForeignLinkingRepositoryFieldImpl extends RepositoryFieldImpl {

    public ForeignLinkingRepositoryFieldImpl(Field field, RepositoryObjectClass<?> parentClass) {
        super(field, parentClass);
    }

    public ForeignLinkingRepositoryFieldImpl(Class<?> fieldType, String id, RepositoryObjectClass<?> parentClass) {
        super(fieldType, id, parentClass);
    }
}
