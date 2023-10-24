package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.exception.NumberNotPresentException;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.SQLConnection;
import net.bytemc.evelon.sql.SQLHelper;

import java.math.BigDecimal;
import java.util.function.Function;

public final class MathCalculationProcess {

    public static <T> long count(RepositoryQuery<T> query) {
        return calculationDatabaseFields("count", SQLHelper::count, -1L, query, null, false);
    }

    public static <T> long sum(RepositoryQuery<T> query, String id) {
        return calculationDatabaseFields("sum", SQLHelper::sum, BigDecimal.valueOf(-1), query, id, true).longValue();
    }

    public static <T> double avg(RepositoryQuery<T> query, String id) {
        return calculationDatabaseFields("avg", SQLHelper::avg, BigDecimal.valueOf(-1), query, id, true).doubleValue();
    }

    private static <T> T calculationDatabaseFields(String key, Function<String[], String> query, T defaultValue, RepositoryQuery<?> repositoryQuery, String id, boolean checkNumber) {
        if (checkNumber && !isRequiredNumberType(repositoryQuery.getRepository().repositoryClass().clazz(), id)) {
            throw new NumberNotPresentException(repositoryQuery.getRepository().repositoryClass().clazz(), id);
        }

        var keyName = key + "_value";
        var querySignature = repositoryQuery.getRepository().getName() + SQLHelper.getDatabaseFilterQuery(repositoryQuery.getFilters());
        var options = (key.equalsIgnoreCase("count") ? new String[]{keyName, querySignature} : new String[]{id, keyName, querySignature});

        return (T) SQLConnection.executeQuery(query.apply(options), resultSet -> {
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
