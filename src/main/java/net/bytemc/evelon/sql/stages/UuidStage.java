package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.UUID;

public final class UuidStage implements ElementStage<UUID> {

    @Override
    public String elementRowData(@Nullable Field field, RepositoryClass<UUID> repository) {
        return DatabaseType.UUID.type();
    }

    @Override
    public Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, UUID object) {
        return new Pair<>(DatabaseHelper.getRowName(field), Schema.encloseSchema(object.toString()));
    }

    @Override
    public UUID createObject(RepositoryClass<UUID> clazz, String id, DatabaseResultSet.Table table) {
        return table.get(id, UUID.class);
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.equals(UUID.class);
    }
}
