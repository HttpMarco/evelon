package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractEntryProcess;
import dev.httpmarco.evelon.repository.RepositoryConstant;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import dev.httpmarco.evelon.sql.parent.HikariRepositoryConstant;
import dev.httpmarco.evelon.sql.parent.SqlType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class HikariPreppedProcess extends AbstractEntryProcess<String, HikariPreppedProcess> {

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS %s (%s);";

    @Override
    public String run(@NotNull RepositoryExternalEntry entry) {
        var sqlEntries = new ArrayList<String>();
        var sqlPrimaryKeys = new ArrayList<String>();

        for (var child : entry.children()) {

            if (child instanceof RepositoryExternalEntry externalEntry) {
                this.newSubProcess(new HikariPreppedProcess());
                continue;
            }

            if(child.constants().has(RepositoryConstant.PRIMARY_KEY)) {
                sqlPrimaryKeys.add(child.id());
            }

            var type = SqlType.find(child);
            // on first initialization, we put the sql type into the constants
            child.constants().put(HikariRepositoryConstant.SQL_TYPE, type);
            sqlEntries.add(child.id() + " " + type);
        }

        if(!sqlPrimaryKeys.isEmpty()) {
            sqlEntries.add("PRIMARY KEY(" + String.join(", ", sqlPrimaryKeys) + ")");
        }
        return CREATE_TABLE_SQL.formatted(entry.id(), String.join(", ", sqlEntries));
    }
}