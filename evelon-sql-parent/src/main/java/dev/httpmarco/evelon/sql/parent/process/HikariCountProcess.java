package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicLong;

public final class HikariCountProcess extends QueryProcess<HikariProcessReference> {

    private static final String COUNT_QUERY = "SELECT COUNT(*) AS ELEMENTS FROM %s;";

    @Override
    public Object run(RepositoryExternalEntry entry, HikariProcessReference reference) {
        AtomicLong count = new AtomicLong(-1);
        reference.append(String.format(COUNT_QUERY, entry.id()), new Object[0], resultSet -> {
            try {
                count.set(resultSet.getLong("ELEMENTS"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return count;
    }
}
