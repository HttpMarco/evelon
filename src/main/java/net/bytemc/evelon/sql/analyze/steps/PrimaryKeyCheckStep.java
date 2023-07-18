package net.bytemc.evelon.sql.analyze.steps;

import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.analyze.AnalyseResult;
import net.bytemc.evelon.sql.analyze.AnalyseStep;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class PrimaryKeyCheckStep extends AnalyseStep {

    @Override
    public boolean detect(@NotNull Repository<?> repository, AnalyseResult result) {

        for (var row : repository.repositoryClass().getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());

            if (stage == null) {
                continue;
            }

            // today we cant check a map or list foreign keys
            if (stage instanceof SubElementStage<?> subElementStage) {
                result.addWarning("Sub tables are not supported yet. You can control this manually.");
                continue;
            }

            List<DatabaseRowData> data = DatabaseHelper.getRowData(repository.getName(), row.getName());
            //todo

        }
        return false;
    }

    @Override
    public void manipulate(@NotNull Repository<?> repository) {
        // ALTER TABLE Employees ADD PRIMARY KEY(ID);
        //todo
    }
}
