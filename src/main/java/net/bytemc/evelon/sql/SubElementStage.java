package net.bytemc.evelon.sql;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;

import java.lang.reflect.Field;
import java.util.List;

public interface SubElementStage<T> extends Stage<T> {

    /**
     * @param table
     * @param current
     * @param field
     * @param keys    possible foreign keys
     * @return the current sql query for create statements
     */
    List<String> onParentTableCollectData(String table, RepositoryClass<?> current, Field field, ForeignKey... keys);

    List<String> onParentElement(String table, Repository<?> parent, RepositoryClass<T> clazz, T value, ForeignKeyObject... keys);

    default List<String> onAnonymousParentElement(String table, Repository<?> parent, RepositoryClass<?> clazz, Object value, ForeignKeyObject... keys) {
        return this.onParentElement(table, parent, (RepositoryClass<T>) clazz, (T) value, keys);
    }

    /**
     * @param table
     * @param clazz
     * @return
     */
    T createInstance(String table, RepositoryClass<T> clazz);

}
