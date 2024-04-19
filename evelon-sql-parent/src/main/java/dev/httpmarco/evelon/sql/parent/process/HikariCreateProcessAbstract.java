package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractObjectProcess;
import dev.httpmarco.evelon.repository.RepositoryConstant;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import dev.httpmarco.evelon.repository.external.RepositoryCollectionEntry;
import dev.httpmarco.evelon.sql.parent.HikariExecutionReference;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public final class HikariCreateProcessAbstract extends AbstractObjectProcess<HikariExecutionReference> {

    private static final String CREATE_VALUE_SQL = "INSERT INTO %s (%s) VALUES (%s);";

    public HikariCreateProcessAbstract(Object value) {
        super(value);
    }

    @Override
    @SneakyThrows
    public @NotNull HikariExecutionReference run(@NotNull RepositoryExternalEntry entry, Object value) {
        var sqlEntries = new ArrayList<String>();
        var reference = new HikariExecutionReference();

        for (var child : entry.children()) {
            var childValue = Reflections.on(child.constants().get(RepositoryConstant.PARAM_FIELD)).value(value);

            if(child instanceof RepositoryCollectionEntry) {
                var collection = (Collection<?>) childValue;
                for (var object : collection) {
                    this.run((RepositoryExternalEntry) child, object);
                    System.err.println(object.toString());
                }
                continue;
            }

            if (child instanceof RepositoryExternalEntry externalEntry) {
                continue;
            }
            arguments().add(childValue);
            sqlEntries.add(child.id());
        }


        if (entry.constants().has(RepositoryConstant.FOREIGN_REFERENCE)) {
            entry.constants().get(RepositoryConstant.FOREIGN_REFERENCE).stream().map(RepositoryEntry::id).forEach(sqlEntries::add);
        }

        return reference.bind(CREATE_VALUE_SQL.formatted(entry.id(),
                String.join(", ", sqlEntries),
                String.join(", ", "?".repeat(sqlEntries.size()).split(""))), arguments().toArray());
    }
}
