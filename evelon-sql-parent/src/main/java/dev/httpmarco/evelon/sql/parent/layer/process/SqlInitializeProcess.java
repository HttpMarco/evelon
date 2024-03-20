package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.common.InitializeProcess;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryClass;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;

public class SqlInitializeProcess extends InitializeProcess {

    private static final String TABLE_CREATE_QUERY = "CREATE TABLE IF NOT EXISTS %s(%s);";

    public SqlInitializeProcess(String id, Repository<?> repository) {
        super(id, repository);
    }

    @Override
    public void pushUpdate() {
        var rows = affectedRows().stream().map(it -> ((RepositoryObjectClass.ObjectField<?>) it)).map(RepositoryObjectClass.ObjectField::id).toList();

        String formattedQuery = TABLE_CREATE_QUERY.formatted(id(), String.join(", ", rows));
        System.err.println(formattedQuery);
    }
}
