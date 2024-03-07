package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.builder.Builder;
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
@SuppressWarnings("unchecked")
public class RepositoryFieldImpl<T> implements RepositoryField<T> {

    private final Field field;
    private final String id;
    private final Class<T> fieldType;

    private final RepositoryClass<T> clazz;
    private final RepositoryObjectClass<?> parentClass;

    public RepositoryFieldImpl(Field field, RepositoryObjectClass<?> parentClass) {
        this.field = field;
        this.id = this.field.getName();
        this.fieldType = (Class<T>) this.field.getType();
        this.clazz = new RepositoryClassImpl<>(this.fieldType);
        this.parentClass = parentClass;
    }

    public RepositoryFieldImpl(Class<?> fieldType, String id, RepositoryObjectClass<?> parentClass) {
        this.field = null;
        this.fieldType = (Class<T>) fieldType;
        this.id = id;

        this.clazz = new RepositoryClassImpl<>(this.fieldType);
        this.parentClass = parentClass;
    }

    @Override
    public <B extends Builder<B, ?>> Stage<T, B> stage(Model<B> model) {
        return model.findStage(this.fieldType);
    }
}