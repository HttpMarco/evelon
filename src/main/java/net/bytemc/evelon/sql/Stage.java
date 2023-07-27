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

import net.bytemc.evelon.repository.RepositoryClass;

/**
 * @param <T> the current element type of layer
 * One stage presents a current java object
 */
public interface Stage<T> {

    /**
     * @param type the class which represents the element
     * @return true if the class is the element of stage
     */
    boolean isElement(Class<?> type);

    /**
     * @param value the class which represents the element
     * @return an adapter for repository classes
     */
    default RepositoryClass<T> newRepositoryClass(Class<?> value) {
        return new RepositoryClass<>((Class<T>) value);
    }

}