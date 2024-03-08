package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

import java.lang.reflect.Field;

public final class PrimaryRepositoryFieldImpl<T> extends RepositoryFieldImpl<T> {

    public PrimaryRepositoryFieldImpl(Repository<?> repository, Field field, RepositoryObjectClass<?> parentClass) {
        super(repository, field, parentClass);
    }

    public PrimaryRepositoryFieldImpl(Repository<?> repository, RepositoryClass<T> clazz, String id, RepositoryObjectClass<?> parentClass) {
        super(repository, clazz, id, parentClass);
    }
}
