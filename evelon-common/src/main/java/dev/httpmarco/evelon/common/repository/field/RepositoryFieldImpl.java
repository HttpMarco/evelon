package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClassImpl;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import lombok.Getter;
import lombok.experimental.Accessors;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.RepositoryField;

import java.lang.reflect.Field;

@Getter
@Accessors(fluent = true)
public class RepositoryFieldImpl implements RepositoryField {

    private final Field field;
    private final String id;
    private final Class<?> fieldType;

    private final RepositoryClass<?> clazz;
    private final RepositoryObjectClass<?> parentClass;

    public RepositoryFieldImpl(Field field, RepositoryObjectClass<?> parentClass) {
        this.field = field;
        this.id = this.field.getName();
        this.fieldType = this.field.getType();
        this.clazz = new RepositoryClassImpl<>(this.fieldType);
        this.parentClass = parentClass;
    }

    public RepositoryFieldImpl(Class<?> fieldType, String id, RepositoryObjectClass<?> parentClass) {
        this.field = null;
        this.fieldType = fieldType;
        this.id = id;

        this.clazz = new RepositoryClassImpl<>(this.fieldType);
        this.parentClass = parentClass;
    }

    @Override
    public Stage<?> stage(Model<?> model) {
        return model.findStage(this.fieldType);
    }
}