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

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public final class RepositoryQueryActionsAsync<T> {

    private final RepositoryQueryActions<T> syncActions;

    public RepositoryQueryActionsAsync(RepositoryQuery<T> query, RepositoryDepartureOrder order) {
        this.syncActions = new RepositoryQueryActions<>(query, order);
    }

    public @NotNull CompletableFuture<List<T>> findAll() {
        return this.supplyAsync(this.syncActions::findAll);
    }

    public CompletableFuture<T> findFirst() {
        return this.supplyAsync(this.syncActions::findFirst);
    }

    public void update(T value) {
        this.runAsync(() -> this.syncActions.update(value));
    }

    public CompletableFuture<Boolean> exists() {
        return this.supplyAsync(this.syncActions::exists);
    }

    public CompletableFuture<Double> avg(String id) {
        return this.supplyAsync(() -> this.syncActions.avg(id));
    }

    public CompletableFuture<Long> count() {
        return this.supplyAsync(this.syncActions::count);
    }

    public CompletableFuture<Long> sum(String id) {
        return this.supplyAsync(() -> this.syncActions.sum(id));
    }

    private void runAsync(Runnable runnable) {
        CompletableFuture.runAsync(runnable).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }

    private <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        return CompletableFuture.supplyAsync(supplier).exceptionally(throwable -> {
            throwable.printStackTrace();
            return null;
        });
    }
}
