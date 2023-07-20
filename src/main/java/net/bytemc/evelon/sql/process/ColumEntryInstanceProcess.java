package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.misc.Pair;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryClass;
import net.bytemc.evelon.sql.DatabaseHelper;
import net.bytemc.evelon.sql.Stage;
import net.bytemc.evelon.sql.StageHandler;
import net.bytemc.evelon.sql.SubElementStage;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class ColumEntryInstanceProcess {

    public static void collect(Repository<?> repository) {

        //  select select_list
        // from t1
        //  inner join t2 using (column1);
    }

    private static List<String> getNeededTables(String children, RepositoryClass<?> clazz) {
        var tables = new ArrayList<String>();
        tables.add(children);

        clazz.getRows()
                .stream()
                .map(field -> new Pair<Stage<?>, Field>(StageHandler.getInstance().getElementStage(field.getType()), field))
                .filter(it -> it.left() instanceof SubElementStage<?>).forEach(it -> {
                    tables.addAll(getNeededTables(children + "_" + DatabaseHelper.getRowName(it.right()),
                            new RepositoryClass<>(it.right().getType())));
                });

        return tables;
    }
}
