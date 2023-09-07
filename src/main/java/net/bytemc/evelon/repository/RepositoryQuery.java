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

package net.bytemc.evelon.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytemc.evelon.local.LocalStorage;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.sql.SQLHelper;
import net.bytemc.evelon.sql.SQLStorage;
import net.bytemc.evelon.StorageHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public final class RepositoryQuery<T> {

    private final List<Filter> filters = new ArrayList<>();
    private final Repository<T> repository;

    public RepositoryQueryActions<T> local() {
        return new RepositoryQueryActions<>(this, RepositoryDepartureOrder.LOCAL);
    }

    public RepositoryQueryActions<T> database() {
        return new RepositoryQueryActions<>(this, RepositoryDepartureOrder.DATABASE);
    }

    public RepositoryQueryActions<T> chronological() {
        return new RepositoryQueryActions<>(this, RepositoryDepartureOrder.CHRONOLOGICAL);
    }

    public RepositoryQueryAsync<T> async() {
        return new RepositoryQueryAsync<>(this);
    }

    public RepositoryQuery<T> filter(Filter filter) {
        this.filters.add(filter);
        return this;
    }

    public void delete() {
        StorageHandler.getStorage(LocalStorage.class).delete(this);
        StorageHandler.getStorage(SQLStorage.class).delete(this);
    }

    public void create(T value) {
        StorageHandler.getStorage(LocalStorage.class).create(this, value);
        StorageHandler.getStorage(SQLStorage.class).create(this, value);
    }

    public void cache(T value) {
        StorageHandler.getStorage(LocalStorage.class).create(this, value);
    }

    public void createIfNotExists(T value) {
        var localStorage = StorageHandler.getStorage(LocalStorage.class);

        for (var primary : getRepository().repositoryClass().getPrimaries()) {
            filter(Filter.match(SQLHelper.getRowName(primary), Reflections.readField(value, primary)));
        }

        if(!localStorage.exists(this)) {
            localStorage.create(this, value);
        }
        var databaseStorage = StorageHandler.getStorage(SQLStorage.class);
        if(!databaseStorage.exists(this)) {
            databaseStorage.create(this, value);
        }
    }

    public void clear() {
        //reset all filters
        this.filters.clear();
        this.delete();
    }
}