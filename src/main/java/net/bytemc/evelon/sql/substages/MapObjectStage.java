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

        var query = new StringBuilder("CREATE TABLE IF NOT EXISTS %s(%s)");
        var elements = new ArrayList<String>();

        for (ForeignKey key : keys) {

            var foreignKeyStage = StageHandler.getInstance().getElementStage(key.foreignKey().getType());

            if (foreignKeyStage == null) {
                throw new StageNotFoundException(key.foreignKey().getType());
            }

            if (foreignKeyStage instanceof ElementStage<?> elementStage) {
                elements.add(DatabaseHelper.getRowName(key.foreignKey()) + " " + elementStage.anonymousElementRowData(key.foreignKey(), new RepositoryClass<>(key.foreignKey().getType())) + " NOT NULL");
            }
        }

        // add map key value -> unique -> primary key
        var keyDatabaseType = keyElementStage.anonymousElementRowData(field, new RepositoryClass<>(keyType));
        if (keyDatabaseType.equals(DatabaseType.TEXT.type())) {
            keyDatabaseType = DatabaseType.VARCHAR.type().formatted("255");
        }
        elements.add(DatabaseHelper.getRowName(field) + "_key " + keyDatabaseType + " PRIMARY KEY");

        if (valueStage instanceof ElementStage<?> elementStage) {
            elements.add(DatabaseHelper.getRowName(field) + "_value " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(keyType)));
        } else {
            //todo
            throw new StageNotSupportedException(valueType);
        }

        if (keys.length != 0) {
            elements.addAll(Arrays.stream(keys).map(it -> "FOREIGN KEY (" + it.parentField() + ") REFERENCES " + it.parentTable() + "(" + it.parentField() + ") ON DELETE CASCADE").toList());
        }
        queries.add(query.toString().formatted(table, String.join(", ", elements)));
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
            var elements = new HashMap<String, String>();
            var query = "INSERT INTO %s(%s) VALUES(%s)";

            for (var foreignKey : keys) {
                elements.put(foreignKey.id(), foreignKey.value());
            }

            if (keyStage instanceof ElementStage<?> elementStage) {
                elements.put(DatabaseHelper.getRowName(field) + "_key", elementStage.anonymousElementEntryData(new RepositoryClass<>(keyType), null, keyObject).right());
            }

            if (valueStage instanceof ElementStage<?> elementStage) {
                elements.put(DatabaseHelper.getRowName(field) + "_value", elementStage.anonymousElementEntryData(new RepositoryClass<>(valueType), null, value.get(keyObject)).right());
            }

            queries.add(query.formatted(table, String.join(", ", elements.keySet()), String.join(", ", elements.values())));
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
