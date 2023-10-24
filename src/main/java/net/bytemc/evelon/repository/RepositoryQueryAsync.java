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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public final class RepositoryQueryAsync<T> {

    @Getter(AccessLevel.PRIVATE)
    private final RepositoryQuery<T> query;

    public RepositoryQueryActionsAsync<T> local() {
        return new RepositoryQueryActionsAsync<>(this.query, RepositoryDepartureOrder.LOCAL);
    }

    public RepositoryQueryActionsAsync<T> database() {
        return new RepositoryQueryActionsAsync<>(this.query, RepositoryDepartureOrder.DATABASE);
    }

    public RepositoryQueryActionsAsync<T> chronological() {
        return new RepositoryQueryActionsAsync<>(this.query, RepositoryDepartureOrder.CHRONOLOGICAL);
    }

    public RepositoryQueryAsync<T> filter(Filter filter) {
        this.query.getFilters().add(filter);
        return this;
    }

    public void delete() {
        this.runAsync(this.query::delete);
    }

    public void create(T value) {
        this.runAsync(() -> this.query.create(value));
    }

    public void createIfNotExists(T value) {
        this.runAsync(() -> this.query.createIfNotExists(value));
    }

    public void clear() {
        this.runAsync(this.query::clear);
    }

    private void runAsync(Runnable runnable) {
        CompletableFuture.runAsync(runnable).exceptionally(throwable -> {
            System.err.println("Probaly error call on async exception: " + throwable.getMessage() + " \n " + throwable.getCause().toString());
            return null;
        });
    }
}
