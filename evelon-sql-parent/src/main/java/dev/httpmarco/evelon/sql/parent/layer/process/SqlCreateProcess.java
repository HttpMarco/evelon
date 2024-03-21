package dev.httpmarco.evelon.sql.parent.layer.process;

import dev.httpmarco.evelon.process.common.CreateProcess;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
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

        response.append(transmitter.executeUpdate(TABLE_CREATE_QUERY.formatted(id(), String.join(", ", affectedRows().
                stream()
                .map(it -> ((RepositoryObjectClass.ObjectField<?>) it).id())
                .toList()), String.join(", ",
                affectedRows().stream().map(it -> ((RepositoryObjectClass.ObjectField<?>) it).fieldValue(value).toString()).toList())), this));

        return response.close();
    }
}