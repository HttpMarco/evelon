package net.bytemc.evelon.sql.substages;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.process.TableCreationProcess;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String query = "CREATE TABLE IF NOT EXISTS %s(%s);";
        // the collected object statements
        var queries = new ArrayList<String>();
        var queryValues = new ArrayList<String>();

        for (var foreignKey : keys) {
            var stage = StageHandler.getInstance().getElementStage(foreignKey.foreignKey().getType());

            if (stage == null) {
                throw new StageNotFoundException(foreignKey.foreignKey().getType());
            }

            if (stage instanceof ElementStage<?> elementStage) {
                queryValues.add(foreignKey.parentField() + " " + elementStage.anonymousElementRowData(foreignKey.foreignKey(), new RepositoryClass<>(foreignKey.foreignKey().getType())).right());
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

        var foreignKeyQuery = Arrays.stream(keys).map(it -> "FOREIGN KEY (" + it.parentField() + ") REFERENCES " + it.parentTable() + "(" + it.parentField() + ")").toList();
        var queryInternal = new StringBuilder(String.join(", ", queryValues)).append(String.join(", ", foreignKeyQuery));

        queries.add(query.formatted(table, queryInternal));

        return queries;
    }



}
