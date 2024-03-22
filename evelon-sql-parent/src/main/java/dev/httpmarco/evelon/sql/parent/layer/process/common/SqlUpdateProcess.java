package dev.httpmarco.evelon.sql.parent.layer.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;
import dev.httpmarco.evelon.sql.parent.layer.process.HikariProcess;

public final class SqlUpdateProcess<T> extends HikariProcess<T> implements Process.Update {

    public SqlUpdateProcess(String id, Query<T> query, boolean mustPrepare, HikariConnectionTransmitter transmitter) {
        super(id, query, mustPrepare, transmitter);
    }

    @Override
    public UpdateResponse pushUpdate() {
        return null;
    }
}
