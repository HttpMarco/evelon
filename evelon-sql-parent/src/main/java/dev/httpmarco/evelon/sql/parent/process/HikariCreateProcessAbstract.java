package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.process.AbstractObjectProcess;
import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.external.RepositoryCollectionEntry;
import dev.httpmarco.evelon.sql.parent.reference.HikariExecutionProcessReference;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class HikariCreateProcessAbstract extends AbstractObjectProcess<HikariExecutionProcessReference> {

    private static final String CREATE_VALUE_SQL = "INSERT INTO %s (%s) VALUES (%s);";

    public HikariCreateProcessAbstract(Object value) {
        super(value);
    }

    @Override
    @SneakyThrows
    public void run(@NotNull RepositoryExternalEntry entry, Object value, HikariExecutionProcessReference reference) {
        var elements = new ArrayList<String>();
        var arguments = new ArrayList<>();

        // todo find maybe a better way
        // Since lists can also have individual attributes as types, we have to check whether these are present,
        // otherwise serialization can be carried out as normal.
        if (entry instanceof RepositoryCollectionEntry collectionEntry && !(collectionEntry.collectionEntry() instanceof RepositoryExternalEntry)) {
            elements.add(collectionEntry.collectionEntry().id());
            arguments.add(value);
        } else {
            for (var child : entry.children()) {
                var childValue = Reflections.on(child.constant(RepositoryConstant.PARAM_FIELD)).value(value);
                if (child instanceof RepositoryExternalEntry externalEntry) {
                    for (var object : externalEntry.readValues(childValue)) {
                        var subprocess = new HikariCreateProcessAbstract(object);

                        // we put all parent primaries in the next process
                        for (var primary : entry.primaries()) {
                            subprocess.property(primary.id(), Reflections.on(primary.constant(RepositoryConstant.PARAM_FIELD)).value(value));
                        }
                        // append the sub process
                        subprocess.run(externalEntry, object, reference);
                    }
                } else {
                    elements.add(child.id());
                    arguments.add(childValue);
                }
            }
        }

        if (entry.hasConstant(RepositoryConstant.FOREIGN_REFERENCE)) {
            for (var foreignKey : entry.constant(RepositoryConstant.FOREIGN_REFERENCE)) {
                arguments.add(0, property(foreignKey.id()));
                elements.add(0, foreignKey.id());
            }
        }
        reference.append(CREATE_VALUE_SQL.formatted(entry.id(), String.join(", ", elements), String.join(", ", "?".repeat(elements.size()).split(""))), arguments.toArray());
    }
}
