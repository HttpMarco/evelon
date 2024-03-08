package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClassImpl;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClassImpl;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.Getter;
import lombok.experimental.Accessors;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

@Getter
@Accessors(fluent = true)
public class RepositoryFieldImpl<T> implements RepositoryField<T> {

    private final Field field;
    private final String id;
    private final Class<T> fieldType;

    private final Repository<?> repository;

    private final RepositoryClass<T> clazz;
    private final RepositoryObjectClass<?> parentClass;

    @SuppressWarnings("unchecked")
    public RepositoryFieldImpl(Repository<?> repository, Field field, RepositoryObjectClass<?> parentClass) {
        this(repository, field.getName(), field, (Class<T>) field.getType(), null, parentClass);
    }

    public RepositoryFieldImpl(Repository<?> repository, RepositoryClass<T> clazz, String id, RepositoryObjectClass<?> parentClass) {
        this(repository, id, null, clazz.clazz(), clazz, parentClass);
    }

    public RepositoryFieldImpl(Repository<?> repository, String id, @Nullable Field field, Class<T> fieldType, @Nullable RepositoryClass<T> clazz, RepositoryObjectClass<?> parentClass) {
        this.id = id;
        this.field = field;
        this.fieldType = fieldType;
        this.repository = repository;
        this.parentClass = parentClass;
        this.clazz = clazz != null ? clazz : new RepositoryClassImpl<>(this.fieldType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T value(Object parent) {
        return (T) Reflections.of(parent).value(field);
    }
}