package net.bytemc.evelon.sql.process;

import net.bytemc.evelon.repository.RepositoryQuery;
import net.bytemc.evelon.sql.SQLConnection;
import net.bytemc.evelon.sql.StageHandler;
import net.bytemc.evelon.sql.substages.VirtualObjectStage;

public final class ColumnUpdateProcess {

    public static <T> void update(RepositoryQuery<T> query, T value) {
        var repository = query.getRepository();

        // get stage of current repository class
        var stage = StageHandler.getInstance().getElementStage(repository.repositoryClass().clazz());

        if(!(stage instanceof VirtualObjectStage virtualObjectStage)) {
            System.err.println("The stage of the repository class " + repository.repositoryClass().clazz().getName() + " is not a virtual object stage. This is not supported.");
            return;
        }
        var queries = virtualObjectStage.onAnonymousUpdateParentElement(query.getRepository().getName(), query.getRepository(), query, query.getRepository().repositoryClass(), value);
        for(int i = queries.size() - 1; i >= 0; i--) {
            SQLConnection.executeUpdate(queries.get(i));
        }
    }
}
