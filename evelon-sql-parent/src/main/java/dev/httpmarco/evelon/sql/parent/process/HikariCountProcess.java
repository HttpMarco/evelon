package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.atomic.AtomicLong;

public final class HikariCountProcess extends QueryProcess<HikariProcessReference, HikariFilter<Object>> {

    private static final String COUNT_QUERY = "SELECT COUNT(*) AS ELEMENTS FROM %s;";

    @Override
    public @NotNull Object run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        AtomicLong count = new AtomicLong(-1);
        reference.append(String.format(COUNT_QUERY, entry.id()), resultSet -> count.set(resultSet.getLong("ELEMENTS")));
        return count;
    }
}
