package net.bytemc.evelon.sql.analyze;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.sql.analyze.steps.PrimaryKeyCheckStep;
import net.bytemc.evelon.sql.analyze.steps.TablenameStep;

public final class TableAnalyseProcess {

    private static AnalyseStep[] steps = new AnalyseStep[]{

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
