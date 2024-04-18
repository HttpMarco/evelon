package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractObjectProcess;
import dev.httpmarco.evelon.repository.RepositoryConstant;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import dev.httpmarco.evelon.sql.parent.HikariExecutionReference;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class HikariCreateProcessAbstract extends AbstractObjectProcess<HikariExecutionReference, HikariCreateProcessAbstract> {

    private static final String CREATE_VALUE_SQL = "INSERT INTO %s (%s) VALUES (%s);";

    public HikariCreateProcessAbstract(Object value) {
        super(value);
    }

    @Override
    @SneakyThrows
    public @NotNull HikariExecutionReference run(@NotNull RepositoryExternalEntry entry, Object o) {
        var sqlEntries = new ArrayList<String>();
        var reference = new HikariExecutionReference();

        for (var child : entry.children()) {
            if (child instanceof RepositoryExternalEntry) {
                this.newSubProcess(new HikariCreateProcessAbstract(null));
                continue;
            }


            sqlEntries.add(child.id());

            // todo with osgan
            var field = child.constants().get(RepositoryConstant.PARAM_FIELD);
            field.setAccessible(true);
            arguments().add(field.get(o));
        }

        reference.stack(CREATE_VALUE_SQL.formatted(entry.id(),
                String.join(", ", sqlEntries),
                String.join(", ", "?".repeat(sqlEntries.size()).split(""))), arguments().toArray());

        return reference;
    }
}
