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

import net.bytemc.evelon.repository.annotations.Ignore;
import net.bytemc.evelon.repository.annotations.PrimaryKey;
import net.bytemc.evelon.sql.ForeignKeyObject;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public record RepositoryClass<T>(Class<T> clazz) {

    /**
     * @return true if the class has a primary key.
     */
    public boolean hasPrimary() {
        return getDeclaredFields().anyMatch(it -> it.isAnnotationPresent(PrimaryKey.class));
    }

    /**
     * @return only the primary keys of the class, if there are than.
     */
    public List<Field> getPrimaries() {
        return getDeclaredFields().filter(it -> it.isAnnotationPresent(PrimaryKey.class)).toList();
    }

    /**
     * @return all fields that are not ignored. Thies' method returns all primaries and rows.
     */
    public List<Field> getRows() {
        List<Field> fields = new ArrayList<>();
        Class<?> tClass = clazz;

        while (tClass != null) {
            fields.addAll(Arrays.stream(tClass.getDeclaredFields()).filter(it -> !it.isAnnotationPresent(Ignore.class)).toList());
            tClass = tClass.getSuperclass();
        }

        return fields;
    }

    private Stream<Field> getDeclaredFields() {
        return Arrays.asList(clazz.getDeclaredFields()).stream();
    }

    /**
     * @param instance the instance of the class
     * @return all primary keys with their values as {@link ForeignKeyObject}
     */
    public ForeignKeyObject[] collectForeignKeyValues(@NotNull Object instance) {
        return getPrimaries().stream().map(it -> ForeignKeyObject.of(it, instance)).toArray(ForeignKeyObject[]::new);
    }
}
