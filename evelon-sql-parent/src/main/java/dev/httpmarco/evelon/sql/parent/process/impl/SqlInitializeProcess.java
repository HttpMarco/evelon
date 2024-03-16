package dev.httpmarco.evelon.sql.parent.process.impl;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.process.Build;
import dev.httpmarco.evelon.common.process.impl.InitializeProcess;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnectionTransmitter;

public class SqlInitializeProcess extends InitializeProcess<StringBuilder> {

    private static final String SQL_QUERY = "CREATE TABLE IF NOT EXISTS %s(%s);";
    private final HikariConnectionTransmitter transmitter;

    public SqlInitializeProcess(Build<StringBuilder> build, HikariConnectionTransmitter transmitter) {
        super(build, new StringBuilder());
        this.transmitter = transmitter;
    }

    @Override
    public void execute() {
        this.transmitter.executeUpdate(SQL_QUERY.formatted(id(), queryContext().toString()), this);
    }

    /*
    @Override
    public void appendQuery(RepositoryField<?> field) {
        queryContext().append(field.id()).append(" ").append(SqlType.find(field.fieldType()));
    }

     */

    @Override
    public void betweenElement() {
        queryContext().append(", ");
    }

    @Override
    public void postQuery() {
        var primaries = rows().stream().filter(RepositoryField::isPrimary).toList();
        if (primaries.isEmpty()) {
            return;
        }
        queryContext().append(", PRIMARY KEY(").append(String.join(", ", primaries.stream().map(RepositoryField::id).toList())).append(")");
    }

    @Override
    public boolean applyRow(Model model, RepositoryField<?> field) {
        return !field.clazz().stageOf(model).isSubStage();
    }
}
