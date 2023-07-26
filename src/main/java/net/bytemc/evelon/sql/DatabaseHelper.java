package net.bytemc.evelon.sql;

import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.AbstractIdFilter;
import net.bytemc.evelon.repository.Filter;
import net.bytemc.evelon.repository.annotations.Row;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseHelper {

    public static boolean isTableExists(String tableName) {
        return DatabaseConnection.executeQuery(("SHOW TABLES LIKE " + Schema.encloseSchema("%s") + ";").formatted(tableName), ResultSet::next, false);
    }

    public static String getRowName(Field field) {
        return (field.isAnnotationPresent(Row.class) ? field.getAnnotation(Row.class).name() : field.getName()).toLowerCase();
    }

    public static List<DatabaseRowData> getRowData(String table, String... rowId) {
        return DatabaseConnection.executeQuery("SHOW COLUMNS FROM %s;".formatted(table), resultSet -> {
            var list = new ArrayList<DatabaseRowData>();
            while (resultSet.next()) {
                list.add(new DatabaseRowData(resultSet.getString("field"), null, null));
            }
            return list;
        }, new ArrayList<>());
    }

    public static String getDatabaseFilterQuery(List<Filter> filters) {
        // if no filter is present lol -> ignore
        if (filters.isEmpty()) {
            return Reflections.EMPTY_STRING;
        }

        return new StringBuilder(" WHERE ").append(String.join(" AND ",
                filters.stream()
                        .filter(filter -> filter instanceof AbstractIdFilter)
                        .map(it -> ((AbstractIdFilter) it))
                        .map(it -> it.sqlFilter(it.getId())).toList())).toString();
    }

    public static String insertDefault(String... values) {
        return "INSERT INTO %s(%s) VALUES(%s);".formatted(values);
    }

    public static String create(String... value) {
        return "CREATE TABLE IF NOT EXISTS %s(%s);".formatted(value);
    }

}
