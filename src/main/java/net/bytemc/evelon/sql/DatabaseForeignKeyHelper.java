package net.bytemc.evelon.sql;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.repository.RepositoryClass;

import java.util.List;

public final class DatabaseForeignKeyHelper {

    public static void convertToDatabaseElementsWithType(List<String> elements, ForeignKey... keys) {
        for (var key : keys) {
            var keyStage = StageHandler.getInstance().getElementStage(key.foreignKey().getType());
            if (keyStage == null) {
                throw new StageNotFoundException(key.foreignKey().getType());
            }
            if (keyStage instanceof ElementStage<?> elementStage) {
                elements.add(DatabaseHelper.getRowName(key.foreignKey()) + " " + elementStage.anonymousElementRowData(key.foreignKey(), new RepositoryClass<>(key.foreignKey().getType())) + " NOT NULL");
            }
        }
    }
}
