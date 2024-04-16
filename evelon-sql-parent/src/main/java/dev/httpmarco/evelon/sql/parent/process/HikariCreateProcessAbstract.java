package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractObjectProcess;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class HikariCreateProcessAbstract extends AbstractObjectProcess<String, HikariCreateProcessAbstract> {

    private static final String CREATE_VALUE_SQL = "INSERT INTO %s (%s) VALUES (%s);";

    public HikariCreateProcessAbstract(Object value) {
        super(value);
    }

    @Override
    public String run(@NotNull RepositoryExternalEntry entry, Object o) {
        var sqlEntries = new ArrayList<String>();
        for (var child : entry.children()) {
            if (child instanceof RepositoryExternalEntry) {
                this.newSubProcess(new HikariCreateProcessAbstract(null));
                continue;
            }
            sqlEntries.add(child.id());
            arguments().add("todo");
        }

        return CREATE_VALUE_SQL.formatted(entry.id(),
                String.join(", ", sqlEntries),
                String.join(", ", "?".repeat(sqlEntries.size()).split("")));
    }
}
