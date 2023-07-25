package net.bytemc.evelon.sql;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import org.jetbrains.annotations.Nullable;

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
    void onParentTableCollectData(List<String> queries, String table, RepositoryClass<?> current, Field field, ForeignKey... keys);

    List<String> onParentElement(String table, Field field, Repository<?> parent, RepositoryClass<T> clazz, T value, ForeignKeyObject... keys);

    default List<String> onAnonymousParentElement(String table, Field field, Repository<?> parent, RepositoryClass<?> clazz, Object value, ForeignKeyObject... keys) {
        return this.onParentElement(table, field, parent, (RepositoryClass<T>) clazz, (T) value, keys);
    }

    /**
     * @param table
     * @param clazz
     * @return
     */
    T createInstance(String table, @Nullable Field parentField, RepositoryClass<T> clazz, DatabaseResultSet resultSet);
}