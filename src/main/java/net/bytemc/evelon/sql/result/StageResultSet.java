package net.bytemc.evelon.sql.result;

import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.DatabaseHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class StageResultSet {

    private Map<String, Object> properties = new HashMap<>();

    public StageResultSet(@NotNull RepositoryClass<?> repositoryClass, ResultSet resultSet) {
        for (var row : repositoryClass.getRows()) {
            var rowName = DatabaseHelper.getRowName(row);

            try {
                this.properties.put(rowName, resultSet.getObject(rowName));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public <T> @Nullable T getData(String id, Class<T> type) {
        return (T) properties.get(id);
    }
}