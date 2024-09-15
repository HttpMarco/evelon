package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.HikariFilterUtil;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import org.jetbrains.annotations.NotNull;

public final class HikariDeleteProcess extends UpdateProcess<HikariProcessReference, HikariFilter<Object>> {

    private static final String DELETE_SQL = "DELETE FROM %s";

    @Override
    public void run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        reference.append(HikariFilterUtil.appendFiltering(DELETE_SQL.formatted(entry.id()), filters()) + ";", filterArguments());
    }
}
