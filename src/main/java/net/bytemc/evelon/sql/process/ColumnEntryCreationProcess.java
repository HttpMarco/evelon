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

package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.*;

import java.util.Collections;

public final class ColumnEntryCreationProcess {

    public static <T> void create(RepositoryQuery<T> query, T value) {

        var stage = StageHandler.getInstance().getElementStage(value.getClass());

        if (stage == null) {
            throw new StageNotFoundException(value.getClass());
        }

        if (stage instanceof SubElementStage<?> subElementStage) {
            var queries = subElementStage.onAnonymousParentElement(query.getRepository().getName(), null, query.getRepository(), query.getRepository().repositoryClass(), value);
            Collections.reverse(queries);

            for (var creationQuery : queries) {
                DatabaseConnection.executeUpdate(creationQuery);
            }
        } else {
            System.err.println("The stage of the repository class " + value.getClass().getName() + " is not a sub element stage. This is not supported.");
        }
    }
}
