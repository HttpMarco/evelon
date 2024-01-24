package dev.httpmarco.evelon.common.repository.field;

import lombok.Getter;
import lombok.experimental.Accessors;
import dev.httpmarco.evelon.common.repository.RepositoryClass;
import dev.httpmarco.evelon.common.repository.RepositoryField;

import java.lang.reflect.Field;

@Getter
@Accessors(fluent = true)
public class RepositoryFieldImpl implements RepositoryField {

    private final String id;
    private final RepositoryClass<?> parentClass;

    public RepositoryFieldImpl(Field field, RepositoryClass<?> parentClass) {
        this.id = field.getName();
        this.parentClass = parentClass;
    }
}
