package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.HikariFilterUtil;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnectionType;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

public final class HikariDeleteProcess extends UpdateProcess<HikariProcessReference, HikariFilter<Object>> {

    private static final String DELETE_SQL = "DELETE FROM %s";

    @Override
    public void run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        reference.directly(HikariConnectionType.UPDATE, HikariFilterUtil.appendFiltering(DELETE_SQL.formatted(entry.id()), filters()) + ";", filterArguments(), ( data) -> data.getUpdateCount() > 0);

        // todo return maybe back
    }
}
