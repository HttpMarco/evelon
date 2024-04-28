package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;

public final class HikariCheckProcess extends QueryProcess<HikariProcessReference> {

    private static final String CHECK_QUERY = "SELECT * FROM %s LIMIT 1;";

    @Override
    public @NotNull Object run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        var result = new AtomicBoolean();
        reference.append(CHECK_QUERY.formatted(entry.id()), it -> result.set(true));
        return result;
    }
}
