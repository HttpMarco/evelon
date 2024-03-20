package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.common.CreateProcess;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;

public class SqlCreateProcess extends CreateProcess {

    private static final String TABLE_CREATE_QUERY = "INSERT INTO %s(%s) VALUES(%s);";
    private final HikariConnectionTransmitter transmitter;

    public SqlCreateProcess(HikariConnectionTransmitter transmitter, String id, Repository<?> repository) {
        super(id, repository);
        this.transmitter = transmitter;
    }

    @Override
    public UpdateResponse pushCreate(Object value) {
        UpdateResponse response = new UpdateResponse();
        return response;
    }
}