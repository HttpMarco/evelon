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

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.annotations.PrimaryKey;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.substages.VirtualObjectStage;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.ArrayList;

public final class TableCreationProcess {

    /**
     * @param repository The repository to create the table for.
     * @apiNote This method is used to create a table for a repository.
     */
    public static void createTable(@NotNull Repository<?> repository) {

        // get stage of current repository class
        var stage = StageHandler.getInstance().getElementStage(repository.repositoryClass().clazz());

        if(!(stage instanceof VirtualObjectStage virtualObjectStage)) {
            System.err.println("The stage of the repository class " + repository.repositoryClass().clazz().getName() + " is not a virtual object stage. This is not supported.");
            return;
        }

        var queries = new ArrayList<String>();
        virtualObjectStage.onParentTableCollectData(queries, repository.getName(), repository.repositoryClass(), null);

        for(int i = queries.size() - 1; i >= 0; i--) {
            SQLConnection.executeUpdate(queries.get(i));
        }
    }

    /**
     * @param field the field which maybe has a primary key annotation
     * @param query current sql type definition
     * @return the query with the primary key appended, if the field has a primary key annotation
     */
    public static String appendPrimaryKey(Field field, String query) {
        if(field.isAnnotationPresent(PrimaryKey.class)) {
            query = query + " PRIMARY KEY";
        }
        return query.toString();
    }
}
