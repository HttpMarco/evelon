package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.QueryProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.AllArgsConstructor;

import java.sql.SQLException;
import java.util.ArrayList;

@AllArgsConstructor
public final class HikariFindProcess extends QueryProcess<HikariProcessReference> {

    private static final String SELECT_QUERY = "SELECT * FROM %s;";
    private final int limit = -1;

    @Override
    public Object run(RepositoryExternalEntry entry, HikariProcessReference reference) {
        var items = new ArrayList<>();

        reference.append(SELECT_QUERY.formatted(entry.id()), new Object[0], resultSet -> {
            var object = Reflections.on(entry.clazz());

            try {
                System.err.println("test: " + resultSet.getFetchSize());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            items.add(object);
        });

        return items;
    }
}
