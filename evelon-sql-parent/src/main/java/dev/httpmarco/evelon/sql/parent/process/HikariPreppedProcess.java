package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.evelon.sql.parent.HikariRepositoryConstant;
import dev.httpmarco.evelon.sql.parent.SqlType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class HikariPreppedProcess extends UpdateProcess<HikariProcessReference> {

    private static final String TABLE_CREATE_QUERY = "CREATE TABLE IF NOT EXISTS %s (%s);";
    private static final String TABLE_VALUE_FORMAT = "%s %s";
    private static final String FOREIGN_FORMAT = "FOREIGN KEY (%s) REFERENCES %s(%s) ON DELETE CASCADE";
    private static final String PRIMARY_FORMAT = "PRIMARY KEY (%s)";

    @Override
    public void run(@NotNull RepositoryExternalEntry entry, HikariProcessReference reference) {
        var elements = new ArrayList<String>();
        var primaries = new ArrayList<String>();

        for (var child : entry.children()) {

            if (child instanceof RepositoryExternalEntry externalEntry) {
                this.run(externalEntry, reference);
                continue;
            }

            if (child.hasConstant(RepositoryConstant.PRIMARY_KEY)) {
                primaries.add(child.id());
            }

            // on first initialization, we put the sql type into the constants
            var type = child.constant(HikariRepositoryConstant.SQL_TYPE, SqlType.detect(child));
            elements.add(TABLE_VALUE_FORMAT.formatted(child.id(), type));
        }

        if (!primaries.isEmpty()) {
            elements.add(PRIMARY_FORMAT.formatted(String.join(", ", primaries)));
        }

        if (entry.hasConstant(RepositoryConstant.FOREIGN_REFERENCE)) {
            for (var foreignKey : entry.constant(RepositoryConstant.FOREIGN_REFERENCE)) {
                // for a better format we put all primaries oder foreign keys in front of the sql entries
                elements.add(0, TABLE_VALUE_FORMAT.formatted(foreignKey.id(), foreignKey.constant(HikariRepositoryConstant.SQL_TYPE)));
                // last we add the foreign constraint
                elements.add(FOREIGN_FORMAT.formatted(foreignKey.id(), foreignKey.parent().id(), foreignKey.id()));
            }
        }
        reference.append(TABLE_CREATE_QUERY.formatted(entry.id(), String.join(", ", elements)));
    }
}