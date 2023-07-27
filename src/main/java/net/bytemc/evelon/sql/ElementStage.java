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

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Map;

public interface ElementStage<T> extends Stage<T> {

    /**
     * @param field is the field of repository property
     * @param repository the class which need rows in a database, can be a repository or a sub entity
     * @return a map with all columns and there sql type
     */
    String elementRowData(@Nullable Field field, RepositoryClass<T> repository);

    /**
     * @param field is the field of repository property
     * @param repository an anonymous class which not know the type
     * @return a map with all columns and there sql type
     */
    default String anonymousElementRowData(@Nullable Field field, RepositoryClass<?> repository){
        return elementRowData(field, (RepositoryClass<T>) repository);
    }

    /**
     * @param repositoryClass is the repository class of the object
     * @param field is the field of repository property
     * @param object the class which type is the stage element type
     * @return a map with the name of database row and the value of the property
     */
    Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, T object);

    /**
     * @param repositoryClass is the repository class of the object
     * @param field is the field of repository property
     * @param object the class which type is the stage element type
     * @return a map with the name of database row and the value of the property
     */
    // left name, right value
    default Pair<String, String> anonymousElementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Object object) {
        return elementEntryData(repositoryClass, field, (T) object);
    }

    /**
     * @param clazz is the repository class of the object
     * @param <T> the type of the object
     * @return a new object of the type
     */
    T createObject(RepositoryClass<T> clazz, String id, DatabaseResultSet.Table table);

    default T anonymousCreateObject(RepositoryClass<?> clazz, String id, DatabaseResultSet.Table table) {
        return createObject((RepositoryClass<T>) clazz, id, table);
    }


}
