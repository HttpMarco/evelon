package net.bytemc.evelon.sql;

import net.bytemc.evelon.repository.RepositoryClass;

import java.lang.reflect.Field;
import java.util.List;

public interface SubElementStage<T> extends Stage<T> {

    /**
     * @param table
     * @param current
     * @param field
     * @param keys possible foreign keys
     * @return the current sql query for create statements
     */
    List<String> onParentTableCollectData(String table, RepositoryClass<?> current, Field field, ForeignKey... keys);

}
