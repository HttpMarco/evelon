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
import net.bytemc.evelon.sql.analyze.steps.PrimaryKeyCheckStep;
import net.bytemc.evelon.sql.analyze.steps.TablenameStep;

public final class TableAnalyseProcess {

    private static final AnalyseStep[] steps = new AnalyseStep[]{

            // The first step is to check if the table name is correct.
            new TablenameStep(),

            // The second step is to check if the table has all primary key.
            new PrimaryKeyCheckStep(),

    };

    public static void run(Repository<?> repository) {
        var result = new AnalyseResult();
        for (var step : steps) {
            if(step.detect(repository, result)) {
                step.manipulate(repository);
            }
        }

        // alert all results of the analyzing
        if (result.isSuccessfully()) {
            return;
        }

        if (!result.getWarnings().isEmpty()) {
            System.out.println("The repository " + repository.getName() + " has some problems:");
            for (String warning : result.getWarnings()) {
                System.out.println("  - " + warning);
            }
        }

        if (!result.getDetections().isEmpty()) {
            System.out.println("The repository " + repository.getName() + " has some detections:");
            for (String detection : result.getDetections()) {
                System.out.println("  - " + detection);
            }
        }
    }
}
