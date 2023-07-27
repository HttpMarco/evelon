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

package net.bytemc.evelon.storages;

import lombok.Getter;
import net.bytemc.evelon.local.LocalStorage;
import net.bytemc.evelon.sql.DatabaseStorage;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public final class StorageHandler {

    @Getter
    private static final List<Storage> storages = new ArrayList<>();

    static {
        //disable logging
        System.setProperty("mariadb.logging.disable", "false");

        storages.add(new DatabaseStorage());
        storages.add(new LocalStorage());
    }

    /**
     * @param clazz the class of the storage
     * @param <T>
     * @return the current storage of the declared class, if not found, it will return null
     */
    public static <T extends Storage> @Nullable T getStorage(Class<T> clazz) {
        return (T) storages.stream().filter(it -> it.getClass().equals(clazz)).findFirst().get();
    }
}
