package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.model.Stage;
import lombok.Getter;
import lombok.experimental.Accessors;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.RepositoryField;

import java.lang.reflect.Field;

@Getter
@Accessors(fluent = true)
public class RepositoryFieldImpl implements RepositoryField {

    private final String id;
    private final Class<?> clazz;
    private final RepositoryClass<?> parentClass;

    public RepositoryFieldImpl(Field field, RepositoryClass<?> parentClass) {
        this.id = field.getName();
        this.clazz = field.getType();
        this.parentClass = parentClass;
    }

    @Override
    public Stage stage() {
        return null;
    }

    @Override
    public Class<?> fieldType() {
        return this.clazz;
    }
}
