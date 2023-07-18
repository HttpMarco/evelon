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
     * This method is called when the step detected a chnage and need the manipulation
     */
    public abstract void manipulate(@NotNull Repository<?> repository);

}
