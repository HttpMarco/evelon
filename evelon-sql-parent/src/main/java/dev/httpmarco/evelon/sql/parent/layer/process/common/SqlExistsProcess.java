package dev.httpmarco.evelon.sql.parent.layer.process.common;

import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;
import dev.httpmarco.evelon.sql.parent.layer.process.HikariProcess;

import java.sql.ResultSet;

public final class SqlExistsProcess<T> extends HikariProcess<T> implements Process.Exists {

    private static final String VALUE_PRESENT_QUERY = "SELECT * FROM %s LIMIT 1;";

    public SqlExistsProcess(String id, Query<T> query, HikariConnectionTransmitter transmitter) {
        super(id, query, false, transmitter);
    }

    @Override
    public QueryResponse<Boolean> queryExists() {
        return transmitter().executeQuery(VALUE_PRESENT_QUERY.formatted(id()), ResultSet::next, this);
    }
}
