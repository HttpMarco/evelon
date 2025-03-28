package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.HikariFilterUtil;
import dev.httpmarco.evelon.sql.parent.connection.HikariConnectionType;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import org.jetbrains.annotations.NotNull;

public final class HikariCountProcess extends QueryProcess<Long, HikariProcessReference, HikariFilter<Object>> {

    private static final String COUNT_QUERY = "SELECT COUNT(*) AS ELEMENTS FROM %s";

    @Override
    public @NotNull Long run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        return reference.directly(HikariConnectionType.QUERY,HikariFilterUtil.appendFiltering(COUNT_QUERY.formatted(entry.id()), filters()) + ";", filterArguments(), data -> {
            if (!data.getResultSet().next()) {
                return -1L;
            }

            return data.getResultSet().getLong("ELEMENTS");
        });
    }
}
