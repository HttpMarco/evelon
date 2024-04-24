package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class HikariConnectionRunner extends ProcessRunner<HikariProcessReference> {

    private final HikariConnection connection;

    @Override
    public void update(HikariProcessReference query) {
        this.connection.update(query);
    }

    @Override
    public HikariProcessReference generateBase() {
        return new HikariProcessReference();
    }

    @Override
    public void query(HikariProcessReference query) {
        this.connection.query(query);
    }
}
