package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.reference.HikariExecutionProcessReference;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class HikariConnectionRunner extends ProcessRunner<HikariExecutionProcessReference> {

    private final HikariConnection connection;

    @Override
    public void update(HikariExecutionProcessReference query) {
        this.connection.update(query);
    }

    @Override
    public void query(HikariExecutionProcessReference query) {
        this.connection.query(query);
    }
}
