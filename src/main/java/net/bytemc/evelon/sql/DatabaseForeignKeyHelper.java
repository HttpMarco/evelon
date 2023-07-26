package net.bytemc.evelon.sql;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.repository.RepositoryClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static void convertToDatabaseForeignLink(List<String> elements, ForeignKey... keys) {
        for (var key : keys) {
            elements.add("FOREIGN KEY (" + key.parentField() + ") REFERENCES " + key.parentTable() + "(" + key.parentField() + ") ON DELETE CASCADE");
        }
    }

    public static Map<String, String> convertKeyObjectsToElements(ForeignKeyObject... keys) {
        var elements = new HashMap<String, String>();
        for (var key : keys) {
            elements.put(key.id(), key.value());
        }
        return elements;
    }

}
