package dev.httpmarco.evelon.sql.parent;

import dev.httpmarco.evelon.process.ProcessRunner;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnection;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public final class HikariConnectionRunner extends ProcessRunner<HikariProcessReference> {

    private final HikariConnection connection;

    @Override
    public void update(HikariProcessReference reference) {
        this.connection.update(reference);
    }

    @Contract(" -> new")
    @Override
    public @NotNull HikariProcessReference generateBase() {
        return new HikariProcessReference();
    }

    @Override
    public void query(HikariProcessReference query) {
        this.connection.query(query);
    }
}