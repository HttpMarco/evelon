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

import net.bytemc.evelon.DatabaseProtocol;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.UUID;

public final class UuidStageSQL implements SQLElementStage<UUID> {

    @Override
    public String elementRowData(@Nullable Field field, RepositoryClass<UUID> repository) {

        if(Evelon.getCradinates().databaseProtocol() == DatabaseProtocol.MYSQL) {
            return SQLType.VARCHAR.toString().formatted(36);
        }

        return SQLType.UUID.toString();
    }

    @Override
    public Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, UUID object) {
        return new Pair<>(SQLHelper.getRowName(field), Schema.encloseSchema(object.toString()));
    }

    @Override
    public UUID createObject(RepositoryClass<UUID> clazz, String id, SQLResultSet.Table table) {
        if(Evelon.getCradinates().databaseProtocol() == DatabaseProtocol.MYSQL) {
            // mysql are noobs because they have no uuid type
            return UUID.fromString(table.get(id, String.class));
        }
        return table.get(id, UUID.class);
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.equals(UUID.class);
    }
}
