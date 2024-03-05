package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

import java.lang.reflect.Field;

public final class PrimaryRepositoryFieldImpl extends ForeignLinkingRepositoryFieldImpl {

    public PrimaryRepositoryFieldImpl(Field field, RepositoryObjectClass<?> parentClass) {
        super(field, parentClass);
    }

    public PrimaryRepositoryFieldImpl(Class<?> fieldType, String id, RepositoryObjectClass<?> parentClass) {
        super(fieldType, id, parentClass);
    }

    public ForeignLinkingRepositoryFieldImpl toForeign() {
        return this;
    }
}
