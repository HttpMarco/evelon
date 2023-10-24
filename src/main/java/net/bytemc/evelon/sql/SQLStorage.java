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

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.process.*;
import net.bytemc.evelon.Storage;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public abstract class SQLStorage implements Storage {

    public SQLStorage() {
        SQLConnection.init(Arrays.stream(DatabaseProtocol.values()).filter(it -> it.getStorageClass().equals(this.getClass())).findFirst().orElse(DatabaseProtocol.MARIADB));
    }

    @Override
    public <T> void create(RepositoryQuery<T> query, T value) {
        ColumnEntryCreationProcess.create(query, value);
    }

    @Override
    public <T> void delete(RepositoryQuery<T> query) {
        //todo
    }

    @Override
    public <T> List<T> findAll(RepositoryQuery<T> query) {
        return ColumEntryInstanceProcess.collect(query, -1);
    }

    @Override
    public <T> @Nullable T findFirst(RepositoryQuery<T> query) {
        final List<T> entries = ColumEntryInstanceProcess.collect(query, 1);
        if (entries.isEmpty()) {
            return null;
        }
        return entries.get(0);
    }


    @Override
    public <T> void update(RepositoryQuery<T> query, T value) {
        ColumnUpdateProcess.update(query, value);
    }

    @Override
    public <T> boolean exists(RepositoryQuery<T> query) {
        return findFirst(query) != null;
    }

    @Override
    public <T> long count(RepositoryQuery<T> query) {
        return MathCalculationProcess.count(query);
    }

    @Override
    public <T> long sum(RepositoryQuery<T> query, String id) {
        return MathCalculationProcess.sum(query, id);
    }

    @Override
    public <T> double avg(RepositoryQuery<T> query, String id) {
        return MathCalculationProcess.avg(query, id);
    }
}
