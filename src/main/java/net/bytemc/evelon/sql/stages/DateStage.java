package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.DatabaseResultSet;
import net.bytemc.evelon.sql.ElementStage;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Date;

public final class DateStage implements ElementStage<Date> {

    @Override
    public Pair<Field, String> elementRowData(@Nullable Field field, RepositoryClass<Date> repository) {
        return null;
    }

    @Override
    public Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Date object) {
        return null;
    }

    @Override
    public Date createObject(RepositoryClass<Date> clazz, String id, DatabaseResultSet.Table table) {
        return table.get(id, Date.class);
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.equals(Date.class);
    }
}
