package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.annotation.PrimaryKey;
import dev.httpmarco.evelon.annotation.Row;
import dev.httpmarco.evelon.stage.Type;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Set;

@Getter
@Accessors(fluent = true)
public final class RepositoryObjectClass<T> extends RepositoryClass<T> {

    // all fields of object class
    private final Set<ObjectField<?>> fields;

    public RepositoryObjectClass(Repository<?> repository, Class<T> originalClass, Type type, @NotNull Set<ObjectField<?>> fields) {
        super(repository, originalClass, type);
        this.fields = fields;

        // set parent after initialize
        this.fields.forEach(it -> it.parent(this));
    }

    /**
     * @return true if object class has no fields
     */
    public boolean isEmpty() {
        return fields.isEmpty();
    }

    /**
     * @return true if object class has primaries
     */
    public boolean hasPrimary() {
        return fields.stream().anyMatch(ObjectField::primary);
    }

    // special class for object fields
    @Getter
    @Accessors(fluent = true)
    public static class ObjectField<T> extends RepositoryClass<T> {

        // field of the object
        private final Field field;

        // field names can be modified with @Row annotation
        private final String id;

        // if field has annotation @PrimaryKey
        private final boolean primary;

        // can not be set in constructor, because it is not initialized yet
        @Setter
        private RepositoryObjectClass<?> parent;

        public ObjectField(Repository<?> repository, Class<T> originalClass, Type type, @NotNull Field field) {
            super(repository, originalClass, type);
            this.field = field;
            this.primary = field.isAnnotationPresent(PrimaryKey.class);
            this.id = field.isAnnotationPresent(Row.class) ? field.getAnnotation(Row.class).name() : field.getName();
        }

        public Object fieldValue(Object parent) {
            return new Reflections<>(this.parent.originalClass()).withValue(parent).value(this.field);
        }
    }
}