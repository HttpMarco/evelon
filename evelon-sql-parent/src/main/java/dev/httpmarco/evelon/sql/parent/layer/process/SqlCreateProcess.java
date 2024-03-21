package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.common.CreateProcess;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;

public class SqlCreateProcess<T> extends CreateProcess<T> {

    private static final String TABLE_CREATE_QUERY = "INSERT INTO %s(%s) VALUES (%s);";
    private final HikariConnectionTransmitter transmitter;

    public SqlCreateProcess(HikariConnectionTransmitter transmitter, String id, Query<T> query) {
        super(id, query);
        this.transmitter = transmitter;
    }

    @Override
    public UpdateResponse pushCreate(Object value) {
        UpdateResponse response = new UpdateResponse();

        response.append(transmitter.executeUpdate(TABLE_CREATE_QUERY.formatted(id(), String.join(", ", affectedRows().
                stream()
                .map(it -> ((RepositoryObjectClass.ObjectField<?>) it).id())
                .toList()), String.join(", ",
                affectedRows().stream().map(it -> "?").toList())), this, affectedRows().stream().map(it -> ((RepositoryObjectClass.ObjectField<?>) it).fieldValue(value)).toArray()));

        return response;
    }
}