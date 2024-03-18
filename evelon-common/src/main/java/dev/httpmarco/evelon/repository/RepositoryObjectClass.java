package dev.httpmarco.evelon.repository;

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

    public RepositoryObjectClass(Class<T> originalClass, Stage.Type type, Set<ObjectField<?>> fields) {
        super(originalClass, type);
        this.fields = fields;

        // set parent after initialize
        fields.forEach(it -> it.parent(this));
    }

    // special class for object fields
    @Getter @Accessors(fluent = true)
    public static class ObjectField<T> extends RepositoryClass<T> {

        // field of the object
        private final Field field;

        // can not be set in constructor, because it is not initialized yet
        @Setter
        private RepositoryObjectClass<?> parent;

        public ObjectField(Class<T> originalClass, Stage.Type type, Field field) {
            super(originalClass, type);
            this.field = field;
        }
    }
}