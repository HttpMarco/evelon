package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

import java.lang.reflect.Field;

public final class ForeignLinkingField  extends RepositoryFieldImpl {

    public ForeignLinkingField(Field field, RepositoryObjectClass<?> parentClass) {
        super(field, parentClass);
    }
}
