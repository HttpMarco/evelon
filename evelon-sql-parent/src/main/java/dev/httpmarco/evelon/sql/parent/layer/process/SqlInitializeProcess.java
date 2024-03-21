package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.common.InitializeProcess;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.sql.parent.layer.SqlType;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;

import java.util.ArrayList;

public class SqlInitializeProcess<T> extends InitializeProcess<T> {

    private static final String TABLE_CREATE_QUERY = "CREATE TABLE IF NOT EXISTS %s(%s);";
    private final HikariConnectionTransmitter transmitter;

    public SqlInitializeProcess(HikariConnectionTransmitter transmitter, String id, Query<T> query) {
        super(id, query);
        this.transmitter = transmitter;
    }

    @Override
    public void pushInitialize() {
        var queryTableParameter = new ArrayList<>(affectedRows()
                .stream()
                .map(it -> ((RepositoryObjectClass.ObjectField<?>) it))
                .map(it -> it.id() + " " + SqlType.find(it.originalClass()))
                .toList());

        var primaryKeyList = affectedRows().stream()
                .map(it -> ((RepositoryObjectClass.ObjectField<?>) it))
                .filter(RepositoryObjectClass.ObjectField::primary)
                .map(RepositoryObjectClass.ObjectField::id)
                .toList();

        if (!primaryKeyList.isEmpty()) {
            queryTableParameter.add("PRIMARY KEY (%s)".formatted(String.join(", ", primaryKeyList)));
        }

        this.transmitter.executeUpdate(TABLE_CREATE_QUERY.formatted(id(), String.join(", ", queryTableParameter)), this);
    }
}
