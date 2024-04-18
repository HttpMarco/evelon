package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class HikariConnectionRunner extends ProcessRunner<HikariExecutionReference> {

    private final HikariConnection connection;

    @Override
    public void update(HikariExecutionReference query) {
        this.connection.update(query);
    }
}
