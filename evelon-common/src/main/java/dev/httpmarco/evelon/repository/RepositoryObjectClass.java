package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.stage.Stage;
import lombok.Getter;
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
    }

    // special class for object fields
    @Getter @Accessors(fluent = true)
    public static class ObjectField<T> extends RepositoryClass<T> {

        private final Field field;

        public ObjectField(Class<T> originalClass, Stage.Type type, Field field) {
            super(originalClass, type);
            this.field = field;
        }
    }
}