package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.HikariFilterUtil;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public final class HikariCheckProcess extends QueryProcess<HikariProcessReference, HikariFilter<Object>> {

    private static final String CHECK_QUERY = "SELECT * FROM %s";

    @Override
    public @NotNull Object run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        var result = new AtomicBoolean();
        reference.append(HikariFilterUtil.appendFiltering(CHECK_QUERY.formatted(entry.id()), filters()) + " LIMIT 1;", filterArguments(), it -> result.set(true));
        return result;
    }
}
