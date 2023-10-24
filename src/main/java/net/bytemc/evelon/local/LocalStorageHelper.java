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

import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Filter;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.repository.RepositoryHelper;
import org.jetbrains.annotations.Nullable;
import java.util.List;
import java.util.stream.Stream;

public final class LocalStorageHelper {

    public static <T> Stream<T> filter(Repository<?> clazz, List<Filter> filters, Stream<T> defaultStream) {
        for (var filter : filters) {
            defaultStream = filter.localFilter(clazz.repositoryClass(), defaultStream);
        }
        return defaultStream;
    }

    /**
     * @param id field name
     * @param parent object with the field
     */
    public static @Nullable Number getNumberFilter(RepositoryClass<?> clazz, String id, Object parent) {
        var element = getObjectFilter(clazz, id, parent);

        if(element == null)  {
            throw new NullPointerException("The field " + id + " is not present.");
        }

        if (!Reflections.isNumber(element.getClass())) {
            System.err.println("Error while filtering: The field " + id + " is not a number.");
            return null;
        }
        return (Number) element;
    }

    public static @Nullable Object getObjectFilter(RepositoryClass<?> clazz, String id, Object parent) {
        // check if superclasses have an equal name
        if(clazz.getRows().stream().filter(it -> RepositoryHelper.getFieldName(it).equalsIgnoreCase(id)).count() > 1) {
            throw new IllegalArgumentException("The field id " + id + " is present in multiple superclasses. For filtering you need to specify the superclass.");
        }
        var fitlerField = clazz.getRows().stream().filter(it -> RepositoryHelper.getFieldName(it).equalsIgnoreCase(id)).findFirst().orElse(null);
        return Reflections.readField(parent, fitlerField);
    }
}
