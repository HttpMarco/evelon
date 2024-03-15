package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.process.Build;
import dev.httpmarco.evelon.common.process.impl.ConstructProcess;
import dev.httpmarco.evelon.common.process.impl.CreateProcess;
import dev.httpmarco.evelon.common.process.impl.InitializeProcess;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnectionTransmitter;
import dev.httpmarco.evelon.sql.parent.process.impl.SqlInitializeProcess;

public class SqlBuild extends Build<StringBuilder> {

    private final HikariConnectionTransmitter transmitter;

    public SqlBuild(Repository<?> repository, Model model, HikariConnection connection) {
        super(repository, model);
        this.transmitter = connection.transmitter();
    }

    @Override
    public InitializeProcess<StringBuilder> initialize() {
        return new SqlInitializeProcess(this, transmitter);
    }

    @Override
    public CreateProcess create() {
        //todo
        return null;
    }

    @Override
    public ConstructProcess construct() {
        //todo
        return null;
    }
}
