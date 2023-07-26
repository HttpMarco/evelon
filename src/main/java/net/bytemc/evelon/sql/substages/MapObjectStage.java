package net.bytemc.evelon.sql.substages;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.exception.StageNotSupportedException;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

public final class MapObjectStage implements SubElementStage<Map<?, ?>> {

    @Override
    public boolean isElement(Class<?> type) {
        return Map.class.isAssignableFrom(type);
    }

    @Override
    public void onParentTableCollectData(List<String> queries, String table, RepositoryClass<?> current, Field field, ForeignKey... keys) {
        var generic = Reflections.readGenericFromClass(field);
        var keyType = generic[0];
        var valueType = generic[1];
        var keyStage = StageHandler.getInstance().getElementStage(keyType);
        var valueStage = StageHandler.getInstance().getElementStage(valueType);

        if (!(keyStage instanceof ElementStage<?> keyElementStage)) {
            throw new StageNotSupportedException(keyType);
        }
        var rowValues = new ArrayList<String>();
        // collect all needed foreign keys
        DatabaseForeignKeyHelper.convertToDatabaseElementsWithType(rowValues, keys);
        // add map key value -> unique -> primary key
        var keyDatabaseType = keyElementStage.anonymousElementRowData(field, new RepositoryClass<>(keyType));
        if (keyDatabaseType.equals(DatabaseType.TEXT.type())) {
            keyDatabaseType = DatabaseType.VARCHAR.type().formatted("255");
        }
        rowValues.add(DatabaseHelper.getRowName(field) + "_key " + keyDatabaseType + " PRIMARY KEY");

        if (valueStage instanceof ElementStage<?> elementStage) {
            rowValues.add(DatabaseHelper.getRowName(field) + "_value " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(keyType)));
        } else {
            //todo
            throw new StageNotSupportedException(valueType);
        }
        // add database schema link
        DatabaseForeignKeyHelper.convertToDatabaseForeignLink(rowValues, keys);
        queries.add(DatabaseHelper.create(table, String.join(", ", rowValues)));
    }

    @Override
    public List<String> onParentElement(String table, Field field, Repository<?> parent, RepositoryClass<Map<?, ?>> clazz, Map<?, ?> value, ForeignKeyObject... keys) {
        //todo: check duplicated code
        var queries = new ArrayList<String>();
        var generic = Reflections.readGenericFromClass(field);
        var keyType = generic[0];
        var valueType = generic[1];
        var keyStage = StageHandler.getInstance().getElementStage(keyType);
        var valueStage = StageHandler.getInstance().getElementStage(valueType);

        for (var keyObject : value.keySet()) {
            var elements = DatabaseForeignKeyHelper.convertKeyObjectsToElements(keys);
            if (keyStage instanceof ElementStage<?> elementStage) {
                elements.put(DatabaseHelper.getRowName(field) + "_key", elementStage.anonymousElementEntryData(new RepositoryClass<>(keyType), null, keyObject).right());
            }
            if (valueStage instanceof ElementStage<?> elementStage) {
                elements.put(DatabaseHelper.getRowName(field) + "_value", elementStage.anonymousElementEntryData(new RepositoryClass<>(valueType), null, value.get(keyObject)).right());
            }
            queries.add(DatabaseHelper.insertDefault(table, String.join(", ", elements.keySet()), String.join(", ", elements.values())));
        }
        return queries;
    }

    @Override
    public Map<?, ?> createInstance(String table, @Nullable Field parentField, RepositoryClass<Map<?, ?>> clazz, DatabaseResultSet resultSet) {
        return DatabaseConnection.executeQuery("SELECT * FROM " + table, result -> {
            var instance = Reflections.allocate(clazz.clazz());
            while (result.next()) {



            }
            return instance;
        }, Reflections.allocate(clazz.clazz()));
    }
}
