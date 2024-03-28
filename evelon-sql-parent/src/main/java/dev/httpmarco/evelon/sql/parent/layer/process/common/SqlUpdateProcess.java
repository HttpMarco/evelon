package dev.httpmarco.evelon.sql.parent.layer.process.common;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.process.Process;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.RepositoryClass;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;
import dev.httpmarco.evelon.sql.parent.layer.filtering.SqlFilter;
import dev.httpmarco.evelon.sql.parent.layer.process.HikariProcess;
import dev.httpmarco.osgan.utils.exceptions.NotImplementedException;

import java.util.stream.Stream;

public final class SqlUpdateProcess<T> extends HikariProcess<T> implements Process.Update {

    private static final String UPDATE_VALUE_QUERY = "UPDATE %s SET %s%s;";

    public SqlUpdateProcess(String id, Query<T> query, HikariConnectionTransmitter transmitter) {
        super(id, query, true, transmitter);
    }

    @Override
    public UpdateResponse pushUpdate(Object value, Layer layer) {
        var rows = affectedRows().stream().map(it -> ((RepositoryObjectClass.ObjectField<?>) it)).filter(it -> !it.primary()).toList();
        var updateValue = rows.stream().map(it -> it.id() + " = ?").toList();
        return transmitter().executeUpdate(UPDATE_VALUE_QUERY.formatted(id(), String.join(",", updateValue), SqlFilter.convertFilters(repository(), query().filters(layer))),
                this, rows.stream().map(it -> it.fieldValue(value)).toArray());
    }
}
