package dev.httpmarco.evelon.sql.parent.layer.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;
import dev.httpmarco.evelon.sql.parent.layer.process.HikariProcess;

public final class SqlDeleteProcess<T> extends HikariProcess<T> implements Process.Delete {

    //todo where filters
    private static final String VALUE_DELETION = "DELETE FROM %s;";

    public SqlDeleteProcess(String id, Query<T> query, HikariConnectionTransmitter transmitter) {
        super(id, query, false, transmitter);
    }

    @Override
    public UpdateResponse pushDeletion() {
        return transmitter().executeUpdate(VALUE_DELETION.formatted(id()), this);
    }
}