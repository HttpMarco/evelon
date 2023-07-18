package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.repository.annotations.PrimaryKey;
import net.bytemc.evelon.sql.DatabaseHelper;
import net.bytemc.evelon.sql.DatabaseType;
import net.bytemc.evelon.sql.ElementStage;
import net.bytemc.evelon.sql.result.StageResultSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.lang.reflect.Field;
import java.util.Map;

public final class DefaultParameterStage implements ElementStage<Object> {

    @Override
    public @Unmodifiable Pair<Field, String> elementRowData(@Nullable Field field, @NotNull RepositoryClass<Object> clazz) {
        var type = DatabaseType.getType(clazz.clazz(), DatabaseType.TEXT, DatabaseType.INT, DatabaseType.BIGINT, DatabaseType.BOOL, DatabaseType.TINYINT, DatabaseType.DOUBLE, DatabaseType.FLOAT);

        // type can be only null if a parameter is not allowed in sql.
        if (type == null) {
            System.err.println("The sql field type for the entity " + clazz.clazz().getSimpleName() + " was not found!");
            return null;
        }

        if (type == DatabaseType.TEXT && field.isAnnotationPresent(PrimaryKey.class)) {
            // mariaDb need a specific length for the primary key. The Default value is 255.
            return new Pair<>(field, "VARCHAR(255)");
        }

        return new Pair<>(field, type.type());
    }

    @Override
    public @NotNull @Unmodifiable Map<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Object object) {
        // mariadb disallow "'" in a boolean value
        if (object instanceof Boolean || object.getClass().equals(boolean.class)) {
            return Map.of(DatabaseHelper.getRowName(field), object.toString());
        }
        return Map.of(DatabaseHelper.getRowName(field), "'" + object + "'");
    }

    @Override
    public <T> T createObject(RepositoryClass<T> clazz, @Nullable Field field, StageResultSet result) {
        return (T) result.getData(DatabaseHelper.getRowName(field), field.getType());
    }

    @Override
    public boolean isElement(@NotNull Class<?> type) {
        return type.isPrimitive() || Reflections.JAVA_ELEMENTS.contains(type);
    }
}
