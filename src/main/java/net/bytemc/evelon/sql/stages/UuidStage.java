package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.DatabaseResultSet;
import net.bytemc.evelon.sql.ElementStage;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.UUID;

public final class UuidStage implements ElementStage<UUID> {

    @Override
    public Pair<Field, String> elementRowData(@Nullable Field field, RepositoryClass<UUID> repository) {
        return null;
    }

    @Override
    public Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, UUID object) {
        return null;
    }

    @Override
    public UUID createObject(RepositoryClass<UUID> clazz, String id, DatabaseResultSet.Table table) {
        return null;
    }

    @Override
    public boolean isElement(Class<?> type) {
        return false;
    }
}
