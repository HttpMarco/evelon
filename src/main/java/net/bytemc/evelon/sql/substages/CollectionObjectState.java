package net.bytemc.evelon.sql.substages;

import net.bytemc.evelon.exception.StageNotFoundException;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CollectionObjectState implements SubElementStage<Collection<?>> {

    @Override
    public boolean isElement(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }

    @Override
    public List<String> onParentTableCollectData(String table, RepositoryClass<?> current, Field field, ForeignKey... keys) {
        var queries = new ArrayList<String>();
        var clazz = Reflections.readGenericFromClass(field)[0];
        var stage = StageHandler.getInstance().getElementStage(clazz);
        if (stage == null) {
            throw new StageNotFoundException(clazz);
        }
        var query = new StringBuilder("CREATE TABLE IF NOT EXISTS %s(%s)");
        var columnValues = new ArrayList<String>();

        for (ForeignKey key : keys) {
            //columnValues.add(key.foreignKey() + " " + key.type());
            //todo
        }

        if (stage instanceof ElementStage<?> elementStage) {
            columnValues.add(DatabaseHelper.getRowName(field) + "_value " + elementStage.anonymousElementRowData(null, new RepositoryClass<>(clazz)).right());
        } else if (stage instanceof SubElementStage<?> subElementStage) {
            //todo: add support for custom stages
        }
        queries.add(query.toString().formatted(table, String.join(", ", columnValues)));
        return queries;
    }

    @Override
    public List<String> onParentElement(String table, Repository<?> parent, RepositoryClass<Collection<?>> clazz, Collection<?> value, ForeignKeyObject... keys) {
        return null;
    }

    @Override
    public Collection<?> createInstance(String table, RepositoryClass<Collection<?>> clazz, DatabaseResultSet resultSet) {
        return null;
    }
}
