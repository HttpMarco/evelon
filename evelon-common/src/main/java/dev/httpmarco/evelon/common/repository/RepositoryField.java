package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.osgan.reflections.Reflections;

import java.lang.reflect.Field;

/**
 * The "RepositoryField" interface defines basic methods required by a field in a repository.
 * Such a RepositoryField represents an attribute within a RepositoryClass.
 */
public interface RepositoryField<T> {

    /**
     * Returns the name of the field, serving as its identifier within the repository.
     *
     * @return The name of the field, serving as its identifier.
     */
    String id();

    /**
     * Returns the RepositoryClass to which the current RepositoryField belongs.
     * The RepositoryClass represents the class or entity to which the field belongs.
     *
     * @return The RepositoryClass to which the RepositoryField belongs.
     */
    RepositoryClass<?> parentClass();

    /**
     * Returns the class of the field
     *
     * @return The class of field
     */
    Class<T> fieldType();

    RepositoryClass<T> clazz();

    Field field();

    @SuppressWarnings("unchecked")
    default T value(Object parent) {
        return (T) Reflections.of(parent).value(field());
    }
}