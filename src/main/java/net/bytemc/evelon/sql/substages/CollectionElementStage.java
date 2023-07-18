package net.bytemc.evelon.sql.substages;

import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.*;
import net.bytemc.evelon.sql.stages.DefaultParameterStage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public final class CollectionElementStage implements SubElementStage<Collection<?>> {

    @Override
    public boolean isElement(Class<?> type) {
        return Collection.class.isAssignableFrom(type);
    }


    @Override
    public List<String> onParentTableCollectData(String table, RepositoryClass<?> current, Field field, ForeignKey... keys) {
        var listType = Reflections.readGenericFromClass(field)[0];
        var listTypeStage = StageHandler.getInstance().getElementStage(listType);

        var sqlQuery = new StringBuilder("CREATE TABLE IF NOT EXISTS %s(&s);");
        var sqlQueryElement = new HashMap<String, String>();

        if (listTypeStage instanceof ElementStage<?> elementStage) {

            // check if a type is only a simple element
            if (elementStage instanceof DefaultParameterStage defaultParameterStage) {
                //Todo
                //    defaultParameterStage.anonymousElementRowData(null, "")
            }

        } else {
            //todo list in list in list is a bad idea
        }

      //sqlQuery.toString().formatted(table)
        //todo
        return new ArrayList<>();
    }
}
