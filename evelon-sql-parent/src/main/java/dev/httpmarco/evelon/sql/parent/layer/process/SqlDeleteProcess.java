package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.common.DeleteProcess;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;

public class SqlDeleteProcess<T> extends DeleteProcess<T> {

    //todo where filters
    private static final String VALUE_DELETION = "DELETE FROM %s;";
    private final HikariConnectionTransmitter transmitter;

    public SqlDeleteProcess(HikariConnectionTransmitter transmitter, String id, Query<T> query) {
        super(id, query);
        this.transmitter = transmitter;
    }

    @Override
    public UpdateResponse pushDelete() {
        var response = new UpdateResponse();
        response.append(transmitter.executeUpdate(VALUE_DELETION.formatted(id()), this));
        return response;
    }
}
