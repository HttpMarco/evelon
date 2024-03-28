package dev.httpmarco.evelon.sql.parent.layer.process.common;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;
import dev.httpmarco.evelon.sql.parent.layer.filtering.SqlFilter;
import dev.httpmarco.evelon.sql.parent.layer.process.HikariProcess;

public final class SqlCountProcess<T> extends HikariProcess<T> implements Process.Count {

    private static final String COUNT_QUERY = "SELECT COUNT(*) AS amountOfContent FROM %s%s;";

    public SqlCountProcess(String id, Query<T> query, HikariConnectionTransmitter transmitter) {
        super(id, query, false, transmitter);
    }

    @Override
    public QueryResponse<Long> queryCount(Layer layer) {
        return transmitter().executeQuery(COUNT_QUERY.formatted(id(), SqlFilter.convertFilters(repository(), query().filters(layer))), it -> {
            if (it.next()) {
                return it.getLong("amountOfContent");
            } else {
                return 0L;
            }
        }, this);
    }
}
