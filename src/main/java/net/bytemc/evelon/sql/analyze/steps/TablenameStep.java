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

package net.bytemc.evelon.sql.analyze.steps;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.annotations.Entity;
import net.bytemc.evelon.sql.DatabaseConnection;
import net.bytemc.evelon.sql.DatabaseHelper;
import net.bytemc.evelon.sql.analyze.AnalyseResult;
import net.bytemc.evelon.sql.analyze.AnalyseStep;
import org.jetbrains.annotations.NotNull;

public final class TablenameStep extends AnalyseStep {

    @Override
    public boolean detect(@NotNull Repository<?> repository, AnalyseResult result) {

        var clazz = repository.repositoryClass().clazz();

        if (clazz.isAnnotationPresent(Entity.class)) {
            var entityName = clazz.getDeclaredAnnotation(Entity.class).name();
            var originalName = clazz.getSimpleName();

            if(originalName.equalsIgnoreCase(entityName)) {
                // if the entity name is the same as the class name, we can assume that the table name is correct.
                return false;
            }

            if(DatabaseHelper.isTableExists(originalName) && !DatabaseHelper.isTableExists(entityName)) {
                result.addDetections("The table name is not correct. The table name should be " + entityName + " instead of " + originalName + ".");
                return true;
            }
        }
        // On this point, we can't do anything.
        // The analyzed process will not start if this is true.
        return false;
    }

    @Override
    public void manipulate(@NotNull Repository<?> repository) {
        // change the name of the current table if exists to the new one.
        DatabaseConnection.executeUpdate("RENAME TABLE IF EXISTS " + repository.repositoryClass().clazz().getSimpleName() + " TO " + repository.getName() + ";");
    }

}
