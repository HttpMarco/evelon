package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.common.ExistsProcess;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.sql.parent.layer.connection.HikariConnectionTransmitter;

import java.sql.ResultSet;
public class SqlExistsProcess extends ExistsProcess {

    private static final String VALUE_PRESENT_QUERY = "SELECT * FROM %s LIMIT 1;";
    private final HikariConnectionTransmitter transmitter;

    public SqlExistsProcess(HikariConnectionTransmitter transmitter, String id, Repository<?> repository) {
        super(id, repository);
        this.transmitter = transmitter;
    }

    @Override
    public QueryResponse<Boolean> queryExists() {
        var queryResponse = new QueryResponse<Boolean>();
        queryResponse.append(transmitter.executeQuery(VALUE_PRESENT_QUERY.formatted(id()), ResultSet::next, this));
        return queryResponse;
    }
}
