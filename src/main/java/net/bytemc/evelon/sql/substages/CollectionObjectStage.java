package net.bytemc.evelon.sql.substages;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.exception.StageNotSupportedException;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;

import java.lang.reflect.Field;
import java.util.*;

public final class CollectionObjectStage implements SubElementStage<Collection<?>> {

    @Override
    public boolean isElement(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }

    @Override
    public void onParentTableCollectData(List<String> queries, String table, RepositoryClass<?> current, Field field, ForeignKey... keys) {
        var clazz = Reflections.readGenericFromClass(field)[0];
        var stage = StageHandler.getInstance().getElementStage(clazz);
        if (stage == null) {
            throw new StageNotFoundException(clazz);
        }
        var query = new StringBuilder("CREATE TABLE IF NOT EXISTS %s(%s)");
        var rowValues = new ArrayList<String>();

        // collect all needed foreign keys
        DatabaseForeignKeyHelper.convertToDatabaseElementsWithType(rowValues, keys);

        var rowName = DatabaseHelper.getRowName(field);
        if (stage instanceof ElementStage<?> elementStage) {
            rowValues.add(rowName + "_value " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(clazz)));
        } else if (stage instanceof SubElementStage<?> subElementStage && subElementStage instanceof VirtualObjectStage) {
            var rowClazz = new RepositoryClass<>(clazz);

            for (var row : rowClazz.getRows()) {
                var rowStage = StageHandler.getInstance().getElementStage(row.getType());
                if (rowStage instanceof ElementStage<?> elementStage) {
                    rowValues.add(DatabaseHelper.getRowName(row) + "_value " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(row.getType())));
                } else {
                    throw new StageNotSupportedException(row.getType());
                }
            }
        } else {
            throw new StageNotSupportedException(clazz);
        }
        // add database schema link
        DatabaseForeignKeyHelper.convertToDatabaseForeignLink(rowValues, keys);
        queries.add(query.toString().formatted(table, String.join(", ", rowValues)));
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
            for (var item : value) {
                var query = "INSERT INTO %s(%s) VALUES (%s);";
                var columns = new HashMap<String, String>();

                for (var foreignKey : keys) {
                    columns.put(foreignKey.id(), foreignKey.value());
                }

                var element = elementStage.anonymousElementEntryData(new RepositoryClass<>(listType), null, item);

                columns.put(DatabaseHelper.getRowName(field) + "_" + element.left(), element.right());
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
                        columns.put(DatabaseHelper.getRowName(row) + "_value", elementStage.anonymousElementEntryData(new RepositoryClass<>(row.getType()), row, object).right());
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
                var repositoryClass = new RepositoryClass<>(listType);
                var table = databaseResultSet.addTable("default");

                if (stage instanceof ElementStage<?> elementStage) {
                    var rowName = DatabaseHelper.getRowName(parentField) + "_value";
                    table.setProperty(rowName, result.getObject(rowName));
                    list.add(elementStage.anonymousCreateObject(repositoryClass, rowName, table));
                } else if (stage instanceof SubElementStage<?> subElementStage && subElementStage instanceof VirtualObjectStage) {
                    var object = Reflections.allocate(listType);

                    for (var row : repositoryClass.getRows()) {
                        var rowStage = StageHandler.getInstance().getElementStage(row.getType());
                        if (rowStage instanceof ElementStage<?> elementStage) {
                            var rowName = DatabaseHelper.getRowName(row) + "_value";
                            table.setProperty(DatabaseHelper.getRowName(row), result.getObject(rowName));
                            Reflections.writeField(object, row, elementStage.anonymousCreateObject(new RepositoryClass<>(row.getType()), DatabaseHelper.getRowName(row), table));
                        } else {
                            throw new StageNotSupportedException(row.getType());
                        }
                    }
                    list.add(object);
                } else {
                    throw new StageNotSupportedException(listType);
                }
            }
            return list;
        }, new ArrayList<>());
    }
}
