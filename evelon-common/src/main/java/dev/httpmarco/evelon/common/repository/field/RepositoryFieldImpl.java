package dev.httpmarco.evelon.common.repository.field;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
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
    private final Class<?> clazz;
    private final RepositoryClass<?> parentClass;

    public RepositoryFieldImpl(Field field, RepositoryClass<?> parentClass) {
        this.field = field;
        this.id = this.field.getName();
        this.clazz = this.field.getType();
        this.parentClass = parentClass;
    }

    /**
     * Constructor only for not field elements (maps or collections)
     * @param clazz the generic type parameter
     * @param id parent name
     * @param parentClass the parent class of list field
     */
    public RepositoryFieldImpl(Class<?> clazz, String id, RepositoryClass<?> parentClass) {
        this.clazz = clazz;
        this.id = id + "_value";
        this.field = null;
        this.parentClass = parentClass;
    }

    @Override
    public Stage<?> stage(Model<?> model) {
        return model.findStage(this.clazz);
    }

    @Override
    public Class<?> fieldType() {
        return this.clazz;
    }
}
