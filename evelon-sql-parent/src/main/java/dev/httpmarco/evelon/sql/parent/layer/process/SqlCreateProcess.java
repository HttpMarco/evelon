package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;

public class SqlCreateProcess<T> extends HikariProcess<T> implements Process.Create {

    private static final String TABLE_CREATE_QUERY = "INSERT INTO %s(%s) VALUES (%s);";

    public SqlCreateProcess(String id, Query<T> query, HikariConnectionTransmitter transmitter) {
        super(id, query, true, transmitter);
    }

    @Override
    public UpdateResponse pushCreation(Object value) {
        return transmitter().executeUpdate(TABLE_CREATE_QUERY.formatted(id(), String.join(", ", affectedRows().
                stream()
                .map(it -> ((RepositoryObjectClass.ObjectField<?>) it).id())
                .toList()), String.join(", ",
                affectedRows().stream().map(it -> "?").toList())), this, affectedRows().stream().map(it -> ((RepositoryObjectClass.ObjectField<?>) it).fieldValue(value)).toArray());
    }
}