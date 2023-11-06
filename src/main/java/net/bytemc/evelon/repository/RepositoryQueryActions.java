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

import lombok.AllArgsConstructor;
import net.bytemc.evelon.Storage;
import net.bytemc.evelon.StorageHandler;
import net.bytemc.evelon.local.LocalStorage;
import net.bytemc.evelon.misc.SortedOrder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@AllArgsConstructor
public final class RepositoryQueryActions<T> {

    private final RepositoryQuery<T> query;
    private final RepositoryDepartureOrder order;

    private void handleStorage(Consumer<Storage> handler) {
        if (order == null) {
            System.err.println("RepositoryDepartureOrder is null, please specify a departure order.");
            return;
        }
        if (order == RepositoryDepartureOrder.CHRONOLOGICAL) {
            handler.accept(StorageHandler.getStorage(LocalStorage.class));
            handler.accept(StorageHandler.getCurrentStorage());
            return;
        }
        handler.accept(StorageHandler.getStorage(order.getStorage().get()));
    }

    public @NotNull List<T> findAll() {
        var elements = new ArrayList<T>();
        handleStorage(storage -> elements.addAll(storage.findAll(query)));
        return elements;
    }

    public T findFirst() {
        var element = new AtomicReference<T>();
        handleStorage(storage -> element.set(storage.findFirst(query)));
        return element.get();
    }

    public void update(T value) {
        handleStorage(storage -> storage.update(query, value));
    }

    public void upsert(T value) {
        handleStorage(storage -> storage.upsert(query, value));
    }

    public boolean exists() {
        if (order == RepositoryDepartureOrder.DATABASE) {
            return StorageHandler.getCurrentStorage().exists(query);
        } else if (order == RepositoryDepartureOrder.LOCAL) {
            return StorageHandler.getStorage(LocalStorage.class).exists(query);
        } else {
            return StorageHandler.getStorage(LocalStorage.class).exists(query) || StorageHandler.getCurrentStorage().exists(query);
        }
    }

    public double avg(String id) {
        var result = new AtomicReference<>(-1.0);
        handleStorage(storage -> result.set(storage.avg(query, id)));
        return result.get();
    }

    public long count() {
        var result = new AtomicLong(-1);
        handleStorage(storage -> result.set(storage.count(query)));
        return result.get();
    }

    public long sum(String id) {
        var result = new AtomicLong(-1);
        handleStorage(storage -> result.set(storage.sum(query, id)));
        return result.get();
    }

    public Collection<T> order(String id, int max, SortedOrder order) {
        return StorageHandler.getCurrentStorage().order(query, id, max, order);
    }
}