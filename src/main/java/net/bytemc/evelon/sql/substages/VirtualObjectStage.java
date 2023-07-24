package net.bytemc.evelon.sql.substages;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.process.TableCreationProcess;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.*;

/**
 * This stage is only for collections of other stages as declared fields.
 */
public final class VirtualObjectStage implements SubElementStage<Object> {

    @Override
    public boolean isElement(Class<?> type) {
        // This stage is the last stage. This can only be a collection of other stages. Always true
        return true;
    }

    @Override
    public List<String> onParentTableCollectData(String table, RepositoryClass<?> current, @Nullable Field field, ForeignKey... keys) {
        var query = "CREATE TABLE IF NOT EXISTS %s(%s);";
        // the collected object statements
        var queries = new ArrayList<String>();
        var queryValues = new ArrayList<String>();

        for (var foreignKey : keys) {
            var stage = StageHandler.getInstance().getElementStage(foreignKey.foreignKey().getType());

            if (stage == null) {
                throw new StageNotFoundException(foreignKey.foreignKey().getType());
            }

            if (stage instanceof ElementStage<?> elementStage) {
                queryValues.add(foreignKey.parentField() + " " + elementStage.anonymousElementRowData(foreignKey.foreignKey(), new RepositoryClass<>(foreignKey.foreignKey().getType())).right() + " NOT NULL");
            } else {
                // primaries can only be elements
                return new ArrayList<>();
            }
        }

        for (var row : current.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());

            if (stage instanceof ElementStage<?> elementStage) {
                // create net repository class, because there can be multiple rows of the same type
                var elementClass = new RepositoryClass<>(row.getType());
                // collect all current rows
                var result = elementStage.anonymousElementRowData(row, elementClass);

                queryValues.add(TableCreationProcess.appendPrimaryKey(result.left(), DatabaseHelper.getRowName(result.left()) + " " + result.right()));
            } else if (stage instanceof SubElementStage<?> subElementStage) {
                queries.addAll(subElementStage.onParentTableCollectData(table + "_" + DatabaseHelper.getRowName(row), new RepositoryClass<>(row.getType()), row, current.getPrimaries().
                        stream().map(it -> new ForeignKey(table, it)).toArray(ForeignKey[]::new)));
            }

        }

        var queryInternal = new StringBuilder(String.join(", ", queryValues));

        if (keys.length > 0) {
            var foreignKeyQuery = Arrays.stream(keys).map(it -> "FOREIGN KEY (" + it.parentField() + ") REFERENCES " + it.parentTable() + "(" + it.parentField() + ") ON DELETE CASCADE").toList();
            queryInternal.append(", ").append(String.join(", ", foreignKeyQuery));
        }

        queries.add(query.formatted(table, queryInternal));
        return queries;
    }

    @Override
    public List<String> onParentElement(String table, Field field, Repository<?> parent, RepositoryClass<Object> clazz, Object value, ForeignKeyObject... keys) {
        var queries = new ArrayList<String>();

        var query = "INSERT INTO %s(%s) VALUES(%s);";
        var values = new HashMap<String, String>();

        for (var foreignKey : keys) {
            values.put(foreignKey.id(), foreignKey.value());
        }

        for (var row : clazz.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());

            if (stage == null) {
                throw new StageNotFoundException(row.getType());
            }

            var object = Reflections.readField(value, row);
            var objectClass = new RepositoryClass<>(row.getType());

            if (stage instanceof SubElementStage<?> subElementStage) {
                queries.addAll(subElementStage.onAnonymousParentElement(parent.appendChildrenName(DatabaseHelper.getRowName(row)), row, parent, objectClass, object, clazz.collectForeignKeyValues(value)));
            } else if (stage instanceof ElementStage<?> elementStage) {
                var result =  elementStage.anonymousElementEntryData(objectClass, row, object);
                values.put(result.left(), result.right());
            }
        }
        queries.add(query.formatted(table, String.join(", ", values.keySet()), String.join(", ", values.values())));
        return queries;
    }

    @Override
    public Object createInstance(String table, Field parentField, RepositoryClass<Object> clazz, DatabaseResultSet databaseResultSet) {
        var object = Reflections.allocate(clazz.clazz());

        for (var row : clazz.getRows()) {
            var stage = StageHandler.getInstance().getElementStage(row.getType());
            if (stage == null) {
                throw new StageNotFoundException(row.getType());
            }

            if (stage instanceof SubElementStage<?> subElementStage) {
                Reflections.writeField(object, row, subElementStage.createInstance(table + "_" + DatabaseHelper.getRowName(row), row, new RepositoryClass(row.getType()), databaseResultSet));
            } else if (stage instanceof ElementStage<?> elementStage) {
                Reflections.writeField(object, row, elementStage.anonymousCreateObject(new RepositoryClass<>(row.getType()), DatabaseHelper.getRowName(row), databaseResultSet.getTable(table)));
            }
        }
        return object;
    }
}
