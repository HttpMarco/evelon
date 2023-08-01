package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.DatabaseConnection;
import net.bytemc.evelon.sql.DatabaseHelper;

import java.util.function.Function;

public final class MathCalculationProcess {

    public static <T> int count(RepositoryQuery<T> query) {
        return calculationDatabaseFields("count", options -> DatabaseHelper.count(options), -1, query, null, false);
    }

    public static <T> int sum(RepositoryQuery<T> query, String id) {
        return calculationDatabaseFields("sum", options -> DatabaseHelper.sum(options), -1, query, id, true);
    }

    public static <T> double avg(RepositoryQuery<T> query, String id) {
        return calculationDatabaseFields("avg", options -> DatabaseHelper.avg(options), -1.0, query, id, true);
    }

    private static <T> T calculationDatabaseFields(String key, Function<String[], String> query, T defaultValue, RepositoryQuery<?> repositoryQuery, String id, boolean checkNumber) {
        if (checkNumber && !isRequiredNumberType(repositoryQuery.getRepository().getClass(), id)) {
            //todo throw exception
        }

        var keyName = key + "_value";
        var querySignature = repositoryQuery.getRepository().getName() + DatabaseHelper.getDatabaseFilterQuery(repositoryQuery.getFilters());
        var options = (key.equalsIgnoreCase("count") ? new String[]{keyName, querySignature} : new String[]{id, keyName, querySignature});

        return (T) DatabaseConnection.executeQuery(query.apply(options), resultSet -> {
            if (resultSet.next()) {
                return resultSet.getObject(keyName);
            }
            return defaultValue;
        }, defaultValue);
    }

    private static boolean isRequiredNumberType(Class<?> clazz, String fieldId) {
        try {
            return Reflections.isNumber(clazz.getDeclaredField(fieldId).getType());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }
}
