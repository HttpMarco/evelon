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
