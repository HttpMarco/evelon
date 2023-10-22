/*
 * Copyright 2019-2023 ByteMC team & contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.bytemc.evelon.sql;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.repository.RepositoryQuery;
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

    List<String> onUpdateParentElement(String table, Field field, Repository<?> parent, RepositoryQuery<T> clazz, T value, ForeignKeyObject... keys);

    default List<String> onAnonymousUpdateParentElement(String table, Field field, Repository<?> parent, RepositoryQuery<?> query, Object value, ForeignKeyObject... keys) {
        return this.onUpdateParentElement(table, field, parent, (RepositoryQuery<T>) query, (T) value, keys);
    }

    /**
     * @param table
     * @param clazz
     * @return
     */
    T createInstance(String table, @Nullable Field parentField, RepositoryClass<T> clazz, SQLResultSet resultSet);

}