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

package net.bytemc.evelon.sql.analyze;

import net.bytemc.evelon.repository.Repository;
import org.jetbrains.annotations.NotNull;

public abstract class AnalyseStep {

    /**
     * @param repository the repository to analyze
     * @param result the result of the analyzing, count warnings and detections
     * @return true if the step was the successfully
     * main analyzed method for the process
     */
    public abstract boolean detect(@NotNull Repository<?> repository, AnalyseResult result);


    /**
     * This method is called when the step detected a change and need the manipulation
     */
    public abstract void manipulate(@NotNull Repository<?> repository);

}
