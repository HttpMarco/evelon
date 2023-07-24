package net.bytemc.evelon.sql.stages;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.DatabaseHelper;
import net.bytemc.evelon.sql.DatabaseResultSet;
import net.bytemc.evelon.sql.DatabaseType;
import net.bytemc.evelon.sql.ElementStage;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.Date;

public final class DateStage implements ElementStage<Date> {

    @Override
    public Pair<Field, String> elementRowData(@Nullable Field field, RepositoryClass<Date> repository) {
        return new Pair<>(field, DatabaseType.DATE.type());
    }

    @Override
    public Pair<String, String> elementEntryData(RepositoryClass<?> repositoryClass, @Nullable Field field, Date object) {
        java.sql.Date date = new java.sql.Date(object.getTime());
        return new Pair<>(DatabaseHelper.getRowName(field), "'" + date + "'");
    }

    @Override
    public Date createObject(RepositoryClass<Date> clazz, String id, DatabaseResultSet.Table table) {
        // this methode is soooo lost hahah
        return new Date(table.get(id, java.sql.Date.class).getTime());
    }

    @Override
    public boolean isElement(Class<?> type) {
        return type.equals(Date.class);
    }
}
