package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractEntryProcess;
import dev.httpmarco.evelon.repository.RepositoryConstant;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import dev.httpmarco.evelon.sql.parent.HikariExecutionReference;
import dev.httpmarco.evelon.sql.parent.HikariRepositoryConstant;
import dev.httpmarco.evelon.sql.parent.SqlType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class HikariPreppedProcess extends AbstractEntryProcess<HikariExecutionReference> {

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS %s (%s);";
    private static final String FOREIGN_FORMAT = "FOREIGN KEY (%s) REFERENCES %s(%s)";
    private static final String PRIMARY_FORMAT = "PRIMARY KEY (%s)";

    @Override
    public @NotNull HikariExecutionReference run(@NotNull RepositoryExternalEntry entry) {

        var sqlEntries = new ArrayList<String>();
        var sqlPrimaryKeys = new ArrayList<String>();
        var reference = new HikariExecutionReference();

        for (var child : entry.children()) {

            if (child instanceof RepositoryExternalEntry externalEntry) {
                reference.append(this.run(externalEntry));
                continue;
            }

            if (child.constants().has(RepositoryConstant.PRIMARY_KEY)) {
                sqlPrimaryKeys.add(child.id());
            }

            var type = SqlType.find(child);
            // on first initialization, we put the sql type into the constants
            child.constants().put(HikariRepositoryConstant.SQL_TYPE, type);
            sqlEntries.add(child.id() + " " + type);
        }

        if (!sqlPrimaryKeys.isEmpty()) {
            sqlEntries.add(PRIMARY_FORMAT.formatted(String.join(", ", sqlPrimaryKeys)));
        }

        if (entry.constants().has(RepositoryConstant.FOREIGN_REFERENCE)) {
            for (var foreignKey : entry.constants().get(RepositoryConstant.FOREIGN_REFERENCE)) {
                sqlEntries.add(foreignKey.id() + " " + foreignKey.constants().get(HikariRepositoryConstant.SQL_TYPE));
                sqlEntries.add(FOREIGN_FORMAT.formatted(foreignKey.id(), foreignKey.parent().id(), foreignKey.id()));
            }
        }

        reference.stack(CREATE_TABLE_SQL.formatted(entry.id(), String.join(", ", sqlEntries)));
        return reference;
    }
}