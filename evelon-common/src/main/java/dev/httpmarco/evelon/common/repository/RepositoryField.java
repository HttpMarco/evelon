package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;

import java.lang.reflect.Field;

/**
 * The "RepositoryField" interface defines basic methods required by a field in a repository.
 * Such a RepositoryField represents an attribute within a RepositoryClass.
 */
public interface RepositoryField<T> {

    /**
     * Returns the name of the field, serving as its identifier within the repository.
     * @return The name of the field, serving as its identifier.
     */
    String id();

    /**
     * Returns the RepositoryClass to which the current RepositoryField belongs.
     * The RepositoryClass represents the class or entity to which the field belongs.
     * @return The RepositoryClass to which the RepositoryField belongs.
     */
    RepositoryClass<?> parentClass();

    /**
     * Returns the class of the field
     * @return The class of field
     */
    Class<T> fieldType();

    /**
     * Get the own class parameters
     * @return the own class as repository class
     */
    RepositoryClass<T> clazz();

    /**
     * Get not the normal repository class, but the java class
     * @return the java basic field
     */
    Field field();

    /**
     * Get the main repository of class
     * @return the stage of the field
     */
    Repository<?> repository();

    /**
     * Get a value of parent object
     * @param parent object
     * @return the field value
     */
    T value(Object parent);

}