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
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public final class LocalStorageHelper {

    public static <T> Stream<T> filter(List<Filter> filters, Stream<T> defaultStream) {

        for (var filter : filters) {
            defaultStream = filter.localFilter(defaultStream);
        }

        return defaultStream;
    }

    /**
     * @param id field name
     * @param parent object with the field
     * @return
     */
    public static @Nullable Number getNumberFilter(String id, Object parent) {
        var element = getObjectFilter(id, parent);
        if (!Reflections.isNumber(element.getClass())) {
            System.err.println("Error while filtering: The field " + id + " is not a number.");
            return null;
        }
        var number = (Number) element;
        return number;
    }

    /**
     * @param id
     * @param parent
     * @return
     */
    public static @Nullable Object getObjectFilter(String id, Object parent) {
        try {
            var fitlerField = parent.getClass().getDeclaredField(id);
            var object = Reflections.readField(parent, fitlerField);
            return object;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }
}
