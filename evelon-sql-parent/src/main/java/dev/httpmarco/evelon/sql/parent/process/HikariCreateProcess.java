package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.common.Pair;
import dev.httpmarco.evelon.external.RepositoryCollectionEntry;
import dev.httpmarco.evelon.external.RepositoryMapEntry;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import dev.httpmarco.evelon.query.QueryConstant;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@AllArgsConstructor
public final class HikariCreateProcess extends UpdateProcess<HikariProcessReference, HikariFilter<Object>> {

    private static final String CREATE_VALUE_SQL = "INSERT INTO %s (%s) VALUES (%s);";
    private final Object value;

    @Override
    @SneakyThrows
    public void run(@NotNull RepositoryExternalEntry entry, HikariProcessReference reference) {
        var elements = new ArrayList<String>();
        var arguments = new ArrayList<>();

        // Since lists can also have individual attributes as types, we have to check whether these are present,
        // otherwise serialization can be carried out as normal.
        if (entry instanceof RepositoryCollectionEntry collectionEntry && !(collectionEntry.typeEntry() instanceof RepositoryExternalEntry)) {
            elements.add(collectionEntry.typeEntry().id());
            arguments.add(value);
            // maps are specific parameters there have not 1 value, rather more.
        } else if (entry instanceof RepositoryMapEntry mapEntry && !(mapEntry.keyEntry() instanceof RepositoryExternalEntry && mapEntry.valueEntry() instanceof RepositoryExternalEntry)) {
            elements.add(mapEntry.keyEntry().id());
            elements.add(mapEntry.valueEntry().id());

            var data = (Pair<?, ?>) value;
            arguments.add(data.first());
            arguments.add(data.second());
        } else {
            for (var child : entry.children()) {
                var childValue = Reflections.on(child.constants().constant(RepositoryConstant.PARAM_FIELD)).value(value);
                if (child instanceof RepositoryExternalEntry externalEntry) {
                    for (var object : externalEntry.readValues(childValue)) {
                        var subprocess = new HikariCreateProcess(object);

                        // we put all parent primaries in the next process
                        subprocess.constants().put(QueryConstant.PRIMARY_SHORTCUT, QueryConstant.PrimaryShortCut.append(entry.primaries(), value));

                        // append the sub process
                        subprocess.run(externalEntry, reference);
                    }
                } else {
                    elements.add(child.id());

                    if (child.constants().has(RepositoryConstant.VALUE_REFACTOR)) {
                        arguments.add(child.constants().constant(RepositoryConstant.VALUE_REFACTOR).apply(childValue));
                    } else {
                        arguments.add(childValue);
                    }
                }
            }
        }

        if (entry.constants().has(RepositoryConstant.FOREIGN_REFERENCE)) {
            for (var foreignKey : entry.constants().constant(RepositoryConstant.FOREIGN_REFERENCE)) {
                arguments.add(0, constants().constant(QueryConstant.PRIMARY_SHORTCUT).value(foreignKey));
                elements.add(0, foreignKey.id());
            }
        }
        reference.append(CREATE_VALUE_SQL.formatted(entry.id(), String.join(", ", elements), String.join(", ", "?".repeat(elements.size()).split(""))), arguments.toArray());
    }
}
