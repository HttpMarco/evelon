package net.bytemc.evelon.sql;

import net.bytemc.evelon.repository.RepositoryClass;

/**
 * @param <T> the current element type of layer
 * One stage presents a current java object
 */
public interface Stage<T> {

    /**
     * @param type the class which represents the element
     * @return true if the class is the element of stage
     */
    boolean isElement(Class<?> type);

    /**
     * @param value the class which represents the element
     * @return an adapter for repository classes
     */
    default RepositoryClass<T> newRepositoryClass(Class<?> value) {
        return new RepositoryClass<>((Class<T>) value);
    }

}