package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import java.util.concurrent.atomic.AtomicBoolean;

public final class HikariCheckProcess extends QueryProcess<HikariProcessReference> {

    private static final String CHECK_QUERY = "SELECT * FROM %s LIMIT 1;";

    @Override
    public Object run(RepositoryExternalEntry entry, HikariProcessReference reference) {
        var result = new AtomicBoolean();

        // todo remove empty objects
        // todo remove exception
        reference.append(CHECK_QUERY.formatted(entry.id()), new Object[0], it -> result.set(true));
        return result;
    }
}
