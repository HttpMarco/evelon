package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Date;

public final class DateStage implements ElementStage<Date> {

    @Override
    public String elementRowData(@Nullable Field field, RepositoryClass<Date> repository) {
        return DatabaseType.DATE.type();
    }

    @Override
    public Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Date object) {
        return new Pair<>(DatabaseHelper.getRowName(field), Schema.encloseSchema(new java.sql.Date(object.getTime())));
    }

    @Override
    public Date createObject(RepositoryClass<Date> clazz, String id, DatabaseResultSet.Table table) {
        return new Date(table.get(id, java.sql.Date.class).getTime());
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.equals(Date.class);
    }
}
