package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.annotations.PrimaryKey;
import dev.httpmarco.evelon.common.model.subs.AbstractVirtualSubStage;
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

    private final boolean primary;

    private final RepositoryClass<T> clazz;
    private @Nullable
    final RepositoryObjectClass<?> parentClass;

    @SuppressWarnings("unchecked")
    public RepositoryFieldImpl(Repository<?> repository, Field field, RepositoryObjectClass<?> parentClass) {
        this(repository, field.getName(), field, (Class<T>) field.getType(), null, parentClass);
    }

    public RepositoryFieldImpl(Repository<?> repository, String id, @Nullable Field field, Class<T> fieldType, @Nullable RepositoryClass<T> clazz, @Nullable RepositoryObjectClass<?> parentClass) {
        this.id = id;
        this.field = field;
        this.primary = field != null && field.isAnnotationPresent(PrimaryKey.class);
        this.fieldType = fieldType;
        this.repository = repository;
        this.parentClass = parentClass;


        var modelLayer = repository.modelLayers().stream().findFirst();
        if (modelLayer.isPresent() && (modelLayer.get().model().findStage(fieldType) instanceof AbstractVirtualSubStage)) {
            this.clazz = new RepositoryObjectClassImpl<>(repository, fieldType);
        } else {
            this.clazz = clazz != null ? clazz : new RepositoryClassImpl<>(this.fieldType);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T value(Object parent) {
        return (T) Reflections.of(parent).value(field);
    }


    @Override
    public boolean isPrimary() {
        return primary;
    }
}