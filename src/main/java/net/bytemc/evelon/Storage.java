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

package net.bytemc.evelon;

import net.bytemc.evelon.repository.RepositoryQuery;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface Storage {

    /**
     * @param query a query to the specific repository
     * @param value an object from the specific repository
     * @param <T> repository type
     */
    <T> void create(RepositoryQuery<T> query, T value);

    <T> void delete(RepositoryQuery<T> query);

    <T> List<T> findAll(RepositoryQuery<T> query);

    <T> @Nullable T findFirst(RepositoryQuery<T> query);

    <T> void update(RepositoryQuery<T> query, T value);

    <T> boolean exists(RepositoryQuery<T> query);

    <T> long count(RepositoryQuery<T> query);

    <T> long sum(RepositoryQuery<T> query, String id);

    <T> double avg(RepositoryQuery<T> query, String id);

    default <T> void createIfNotExists(RepositoryQuery<T> query, T value) {
        if(!exists(query)) {
            create(query, value);
        }
    }
}
