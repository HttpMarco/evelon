package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.annotation.PrimaryKey;
import dev.httpmarco.evelon.annotation.Row;
import dev.httpmarco.evelon.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.lang.reflect.Field;
import java.util.Set;

@Getter
@Accessors(fluent = true)
public final class RepositoryObjectClass<T> extends RepositoryClass<T> {

    // all fields of object class
    private final Set<ObjectField<?>> fields;

    public RepositoryObjectClass(Repository<?> repository, Class<T> originalClass, Stage.Type type, Set<ObjectField<?>> fields) {
        super(repository, originalClass, type);
        this.fields = fields;

        // set parent after initialize
        fields.forEach(it -> it.parent(this));
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

        public ObjectField(Repository<?> repository, Class<T> originalClass, Stage.Type type, Field field) {
            super(repository, originalClass, type);
            this.field = field;

            this.primary = field.isAnnotationPresent(PrimaryKey.class);

            if (field.isAnnotationPresent(Row.class)) {
                this.id = field.getAnnotation(Row.class).name();
            } else {
                this.id = field.getName();
            }
        }
    }
}