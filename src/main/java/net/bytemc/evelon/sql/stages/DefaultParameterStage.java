package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.repository.annotations.PrimaryKey;
import net.bytemc.evelon.sql.DatabaseHelper;
import net.bytemc.evelon.sql.DatabaseResultSet;
import net.bytemc.evelon.sql.DatabaseType;
import net.bytemc.evelon.sql.ElementStage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;
import java.lang.reflect.Field;

public final class DefaultParameterStage implements ElementStage<Object> {

    @Override
    public @Unmodifiable Pair<Field, String> elementRowData(@Nullable Field field, @NotNull RepositoryClass<Object> clazz) {
        var type = DatabaseType.getType(clazz.clazz(), DatabaseType.TEXT, DatabaseType.INT, DatabaseType.BIGINT, DatabaseType.BOOL, DatabaseType.TINYINT, DatabaseType.DOUBLE, DatabaseType.FLOAT);

        // type can be only null if a parameter is not allowed in sql.
        if (type == null) {
            System.err.println("The sql field type for the entity " + clazz.clazz().getSimpleName() + " was not found!");
            return null;
        }

        if (type == DatabaseType.TEXT && field != null && field.isAnnotationPresent(PrimaryKey.class)) {
            // mariaDb need a specific length for the primary key. The Default value is 255.
            return new Pair<>(field, "VARCHAR(255)");
        }

        return new Pair<>(field, type.type());
    }

    @Override
    public @NotNull @Unmodifiable Pair<@Nullable String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Object object) {
        // mariadb disallow "'" in a boolean value
        var fieldName = field == null ? "value" : DatabaseHelper.getRowName(field);

        if (object instanceof Boolean || object.getClass().equals(boolean.class)) {
            return new Pair<>(fieldName, object.toString());
        }
        return new Pair<>(fieldName, "'" + object + "'");
    }

    @Override
    public Object createObject(RepositoryClass<Object> clazz, String id, DatabaseResultSet.Table table) {
        return table.get(id, clazz.clazz());
    }

    @Override
    public boolean isElement(@NotNull Class<?> type) {
        return type.isPrimitive() || Reflections.JAVA_ELEMENTS.contains(type);
    }
}
