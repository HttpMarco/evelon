package dev.httpmarco.evelon.common.repository;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;

/**
 * The "RepositoryField" interface defines basic methods required by a field in a repository.
 * Such a RepositoryField represents an attribute within a RepositoryClass.
 */
public interface RepositoryField {

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
     * Returns the stage of the field
     *
     * @return The stage of field
     */
    Stage<?> stage(Model<?> model);

    /**
     * Returns the class of the field
     *
     * @return The class of field
     */
    Class<?> fieldType();

}
