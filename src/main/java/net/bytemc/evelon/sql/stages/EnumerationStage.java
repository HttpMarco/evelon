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

package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Arrays;

public final class EnumerationStage implements ElementStage<Enum<?>> {

    @Override
    public String elementRowData(@Nullable Field field, RepositoryClass<Enum<?>> repository) {
        var type = (Class<Enum<?>>) field.getType();
        return DatabaseType.ENUM.type().formatted(String.join(",", Arrays.stream(type.getEnumConstants()).map(it -> Schema.encloseSchema(it.name())).toList()));
    }

    @Override
    public Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Enum<?> object) {
        return new Pair<>(DatabaseHelper.getRowName(field), Schema.encloseSchema(object.name()));
    }

    @Override
    public Enum<?> createObject(RepositoryClass clazz, String id, DatabaseResultSet.Table table) {
        return Enum.valueOf((Class<? extends Enum>) clazz.clazz(), table.get(id, String.class));
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.isEnum();
    }
}
