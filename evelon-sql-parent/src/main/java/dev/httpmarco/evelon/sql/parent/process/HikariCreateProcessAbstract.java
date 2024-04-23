package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractObjectProcess;
import dev.httpmarco.evelon.repository.RepositoryConstant;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import dev.httpmarco.evelon.sql.parent.HikariExecutionReference;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.UUID;

public final class HikariCreateProcessAbstract extends AbstractObjectProcess<HikariExecutionReference> {

    private static final String CREATE_VALUE_SQL = "INSERT INTO %s (%s) VALUES (%s);";

    public HikariCreateProcessAbstract(Object value) {
        super(value);
    }

    @Override
    @SneakyThrows
    public @NotNull HikariExecutionReference run(@NotNull RepositoryExternalEntry entry, Object value) {
        var reference = new HikariExecutionReference();
        var elements = new ArrayList<String>();
        var arguments = new ArrayList<>();

        for (var child : entry.children()) {
            var childValue = Reflections.on(child.constants().get(RepositoryConstant.PARAM_FIELD)).value(value);

            if (child instanceof RepositoryExternalEntry externalEntry) {
                for (var object : externalEntry.readValues(childValue)) {
                    reference.append(new HikariCreateProcessAbstract(childValue).run(externalEntry, object));
                }
            } else {
                elements.add(child.id());
                arguments.add(childValue);
            }
        }

        if (entry.constants().has(RepositoryConstant.FOREIGN_REFERENCE)) {
            for (var foreignKey : entry.constants().get(RepositoryConstant.FOREIGN_REFERENCE)) {

                // todo: get foreign key value
                arguments.add(0, UUID.randomUUID());
                elements.add(0, foreignKey.id());
            }
        }
        return reference.bind(CREATE_VALUE_SQL.formatted(entry.id(), String.join(", ", elements), String.join(", ", "?".repeat(elements.size()).split(""))), arguments.toArray());
    }
}
