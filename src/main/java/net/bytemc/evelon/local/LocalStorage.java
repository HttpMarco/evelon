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

package net.bytemc.evelon.local;

import net.bytemc.evelon.exception.NumberNotPresentException;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.misc.SortedOrder;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.Storage;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class LocalStorage implements Storage {

    private final Map<Repository<?>, List<?>> cache = new HashMap<>();

    @Override
    public <T> void create(RepositoryQuery<T> query, T value) {
        Reflections.writeList((List<T>) this.cache.get(query.getRepository()), value);
    }

    @Override
    public <T> void upsert(RepositoryQuery<T> query, T value) {
    }

    @Override
    public <T> void delete(RepositoryQuery<T> query) {
        var elements = cache.get(query.getRepository());
        var deletableEntries = LocalStorageHelper.filter(query.getRepository(), query.getFilters(), elements.stream()).toList();

        var objects = cache.get(query.getRepository());
        objects.removeAll(deletableEntries);
        this.cache.put(query.getRepository(), objects);
    }

    @Override
    public <T> List<T> findAll(RepositoryQuery<T> query) {
        return LocalStorageHelper.filter(query.getRepository(), query.getFilters(), cache.get(query.getRepository()).stream()).map(element -> (T) element).toList();
    }

    @Override
    public <T> @Nullable T findFirst(RepositoryQuery<T> query) {
        var elements = findAll(query);
        if (elements.isEmpty()) {
            return null;
        }
        return elements.get(0);
    }

    @Override
    public <T> void update(RepositoryQuery<T> query, T value) {
        // I think this is the right way to do it -> nothing
    }

    @Override
    public <T> boolean exists(RepositoryQuery<T> query) {
        return findFirst(query) != null;
    }

    @Override
    public <T> long count(RepositoryQuery<T> query) {
        return findAll(query).size();
    }

    @Override
    public <T> long sum(RepositoryQuery<T> query, String id) {
        if (Reflections.isNumberFromField(query.getRepository().repositoryClass().clazz(), id)) {
            return findAll(query).stream().mapToInt(value -> (int) Reflections.readFieldFromId(value, id)).sum();
        } else {
            throw new NumberNotPresentException(query.getRepository().repositoryClass().clazz(), id);
        }
    }

    @Override
    public <T> double avg(RepositoryQuery<T> query, String id) {
        if (Reflections.isNumberFromField(query.getRepository().repositoryClass().clazz(), id)) {
            return findAll(query).stream().mapToInt(value -> (int) Reflections.readFieldFromId(value, id)).average().getAsDouble();
        } else {
            throw new NumberNotPresentException(query.getRepository().repositoryClass().clazz(), id);
        }
    }

    @Override
    public <T> List<T> order(RepositoryQuery<T> query, String id, int max, SortedOrder order) {
        //TODO
        return null;
    }

    public void initializeRepository(Repository<?> repository) {
        this.cache.put(repository, new CopyOnWriteArrayList<>());
    }
}
