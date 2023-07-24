package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.DatabaseHelper;
import net.bytemc.evelon.sql.DatabaseResultSet;
import net.bytemc.evelon.sql.DatabaseType;
import net.bytemc.evelon.sql.ElementStage;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public final class EnumerationStage implements ElementStage<Enum<?>> {

    @Override
    public Pair<Field, String> elementRowData(@Nullable Field field, RepositoryClass<Enum<?>> repository) {
        var type = (Class<Enum<?>>) field.getType();
        return new Pair<>(field, DatabaseType.ENUM.type().formatted(String.join(",", Arrays.stream(type.getEnumConstants()).map(it -> "'" + it.name() + "'").toList())));
    }

    @Override
    public Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Enum<?> object) {
        return new Pair<>(DatabaseHelper.getRowName(field), "'" + object.name() + "'");
    }

    @Override
    public Enum<?> createObject(RepositoryClass clazz, String id, DatabaseResultSet.Table table) {
        return Enum.valueOf((Class<? extends Enum>) clazz.clazz(), table.get(id, String.class));
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.isEnum();
    }
}
