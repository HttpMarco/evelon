package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.nio.file.Path;

public final class PathStage implements ElementStage<Path> {

    @Override
    public String elementRowData(@Nullable Field field, RepositoryClass<Path> repository) {
        return DatabaseType.TEXT.type();
    }

    @Override
    public Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Path object) {
        // change path symbol, because mariadb ignore this symbol
        return new Pair<>(DatabaseHelper.getRowName(field), Schema.encloseSchema(object.toString().replaceAll("\\\\", "/")));
    }

    @Override
    public Path createObject(RepositoryClass<Path> clazz, String id, DatabaseResultSet.Table table) {
        return Path.of(table.get(id, String.class));
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.equals(Path.class);
    }
}
