package net.bytemc.evelon.sql.substages;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.exception.StageNotSupportedException;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;

import java.lang.reflect.Field;
import java.util.*;

public final class CollectionObjectState implements SubElementStage<Collection<?>> {

    @Override
    public boolean isElement(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }

    @Override
    public List<String> onParentTableCollectData(String table, RepositoryClass<?> current, Field field, ForeignKey... keys) {
        var queries = new ArrayList<String>();
        var clazz = Reflections.readGenericFromClass(field)[0];
        var stage = StageHandler.getInstance().getElementStage(clazz);
        if (stage == null) {
            throw new StageNotFoundException(clazz);
        }
        var query = new StringBuilder("CREATE TABLE IF NOT EXISTS %s(%s)");
        var columnValues = new ArrayList<String>();

        // todo merge with @see VirtualObjectStage
        for (ForeignKey key : keys) {

            var keyStage = StageHandler.getInstance().getElementStage(key.foreignKey().getType());

            if (keyStage == null) {
                throw new StageNotFoundException(key.foreignKey().getType());
            }


            if (keyStage instanceof ElementStage<?> elementStage) {
                columnValues.add(DatabaseHelper.getRowName(key.foreignKey()) + " " + elementStage.anonymousElementRowData(key.foreignKey(), new RepositoryClass<>(key.foreignKey().getType())).right() + " NOT NULL");
            }
        }

        var rowName = DatabaseHelper.getRowName(field);
        if (stage instanceof ElementStage<?> elementStage) {
            columnValues.add(rowName + "_value " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(clazz)).right());
        } else if (stage instanceof SubElementStage<?> subElementStage && subElementStage instanceof VirtualObjectStage) {
            var rowClazz = new RepositoryClass<>(clazz);

            for (var row : rowClazz.getRows()) {
                var rowStage = StageHandler.getInstance().getElementStage(row.getType());
                if (rowStage instanceof ElementStage<?> elementStage) {
                    columnValues.add(DatabaseHelper.getRowName(row) + "_value " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(row.getType())).right());
                } else {
                    throw new StageNotSupportedException(row.getType());
                }
            }
        } else {
            throw new StageNotSupportedException(clazz);
        }

        if (keys.length != 0) {
            columnValues.addAll(Arrays.stream(keys).map(it -> "FOREIGN KEY (" + it.parentField() + ") REFERENCES " + it.parentTable() + "(" + it.parentField() + ") ON DELETE CASCADE").toList());
        }

        queries.add(query.toString().formatted(table, String.join(", ", columnValues)));
        return queries;
    }

    @Override
    public List<String> onParentElement(String table, Field field, Repository<?> parent, RepositoryClass<Collection<?>> clazz, Collection<?> value, ForeignKeyObject... keys) {
        var queries = new ArrayList<String>();
        var listType = Reflections.readGenericFromClass(field)[0];
        var stage = StageHandler.getInstance().getElementStage(listType);

        if (stage == null) {
            throw new StageNotFoundException(listType);
        }

        //todo remove duplicated code
        if (stage instanceof ElementStage<?> elementStage) {
            for (var element : value) {
                var query = "INSERT INTO %s(%s) VALUES (%s);";
                var columns = new HashMap<String, String>();

                for (var foreignKey : keys) {
                    columns.put(foreignKey.id(), foreignKey.value());
                }

                var elements = elementStage.anonymousElementEntryData(new RepositoryClass<>(listType), null, element);
                for (var name : elements.keySet()) {
                    columns.put(DatabaseHelper.getRowName(field) + "_" + name, elements.get(name));
                }
                queries.add(query.formatted(table, String.join(", ", columns.keySet()), String.join(", ", columns.values())));
            }
        } else if (stage instanceof SubElementStage<?> subElementStage && subElementStage instanceof VirtualObjectStage) {

            for (var element : value) {
                var query = "INSERT INTO %s(%s) VALUES (%s);";
                var columns = new HashMap<String, String>();

                for (var foreignKey : keys) {
                    columns.put(foreignKey.id(), foreignKey.value());
                }

                var rowClazz = new RepositoryClass<>(listType);

                for (var row : rowClazz.getRows()) {
                    var rowStage = StageHandler.getInstance().getElementStage(row.getType());
                    if (rowStage instanceof ElementStage<?> elementStage) {
                        var object = Reflections.readField(element, row);
                        columns.put(DatabaseHelper.getRowName(row) + "_value",  elementStage.anonymousElementEntryData(new RepositoryClass<>(row.getType()), row, object).get(DatabaseHelper.getRowName(row)));
                    } else {
                        throw new StageNotSupportedException(row.getType());
                    }
                }
                queries.add(query.formatted(table, String.join(", ", columns.keySet()), String.join(", ", columns.values())));
            }

        } else {
            throw new StageNotSupportedException(listType);
        }
        return queries;
    }

    @Override
    public Collection<?> createInstance(String tableName, Field parentField, RepositoryClass<Collection<?>> clazz, DatabaseResultSet resultSet) {

        var listType = Reflections.readGenericFromClass(parentField)[0];

        // we can't use the result, because we need to collect all elements
        return DatabaseConnection.executeQuery("SELECT * FROM " + tableName + ";", (result) -> {
            var list = new ArrayList<>();
            var stage = StageHandler.getInstance().getElementStage(listType);
            if (stage == null) {
                throw new StageNotFoundException(clazz.clazz());
            }

            while (result.next()) {
                var databaseResultSet = new DatabaseResultSet();
                var table = databaseResultSet.addTable("default");

                var columnName = DatabaseHelper.getRowName(parentField) + "_value";
                table.setProperty(columnName, result.getObject(columnName));
                if (stage instanceof ElementStage<?> elementStage) {
                    list.add(elementStage.createObject(new RepositoryClass<>(listType), columnName, table));
                } else {
                    //todo
                }
            }
            return list;
        }, new ArrayList<>());
    }
}
