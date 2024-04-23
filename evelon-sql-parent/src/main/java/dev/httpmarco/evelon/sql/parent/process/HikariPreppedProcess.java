package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractEntryProcess;
import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.sql.parent.HikariExecutionReference;
import dev.httpmarco.evelon.sql.parent.HikariRepositoryConstant;
import dev.httpmarco.evelon.sql.parent.SqlType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class HikariPreppedProcess extends AbstractEntryProcess<HikariExecutionReference> {

    private static final String TABLE_CREATE_QUERY = "CREATE TABLE IF NOT EXISTS %s (%s);";
    private static final String TABLE_VALUE_FORMAT = "%s %s";
    private static final String FOREIGN_FORMAT = "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE";
    private static final String PRIMARY_FORMAT = "PRIMARY KEY (%s)";

    @Override
    public @NotNull HikariExecutionReference run(@NotNull RepositoryExternalEntry entry) {
        var elements = new ArrayList<String>();
        var primaries = new ArrayList<String>();
        var reference = new HikariExecutionReference();

        for (var child : entry.children()) {

            if (child instanceof RepositoryExternalEntry externalEntry) {
                reference.append(this.run(externalEntry));
                continue;
            }

            if (child.constants().has(RepositoryConstant.PRIMARY_KEY)) {
                primaries.add(child.id());
            }

            // on first initialization, we put the sql type into the constants
            var type = child.constants().put(HikariRepositoryConstant.SQL_TYPE, SqlType.detect(child));
            elements.add(TABLE_VALUE_FORMAT.formatted(child.id(), type));
        }

        if (!primaries.isEmpty()) {
            elements.add(PRIMARY_FORMAT.formatted(String.join(", ", primaries)));
        }

        if (entry.constants().has(RepositoryConstant.FOREIGN_REFERENCE)) {
            for (var foreignKey : entry.constants().get(RepositoryConstant.FOREIGN_REFERENCE)) {
                // for a better format we put all primaries oder foreign keys in front of the sql entries
                elements.add(0, TABLE_VALUE_FORMAT.formatted(foreignKey.id(), foreignKey.constants().get(HikariRepositoryConstant.SQL_TYPE)));
                // last we add the foreign constraint
                elements.add(FOREIGN_FORMAT.formatted(foreignKey.id(), foreignKey.parent().id(), foreignKey.id()));
            }
        }
        return reference.bind(TABLE_CREATE_QUERY.formatted(entry.id(), String.join(", ", elements)));
    }
}