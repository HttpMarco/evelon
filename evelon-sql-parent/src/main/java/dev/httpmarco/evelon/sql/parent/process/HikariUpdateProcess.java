package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.external.RepositoryCollectionEntry;
import dev.httpmarco.evelon.external.RepositoryMapEntry;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import dev.httpmarco.evelon.query.QueryConstant;
import dev.httpmarco.evelon.sql.parent.HikariFilter;
import dev.httpmarco.evelon.sql.parent.HikariFilterUtil;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@AllArgsConstructor
public final class HikariUpdateProcess extends UpdateProcess<HikariProcessReference, HikariFilter<Object>> {

    private static final String UPDATE_VALUE = "UPDATE %s SET %s";
    private Object value;

    @Override
    public void run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        var elements = new ArrayList<String>();
        var arguments = new ArrayList<>();

        if (entry instanceof RepositoryCollectionEntry collectionEntry) {
            // delete
            var deleteProcess = new HikariDeleteProcess();

            for (var primary : entry.parent().primaries()) {
                deleteProcess.filters().add(new HikariFilter.SequenceMatchFilter(primary.id(), constants().constant(QueryConstant.PRIMARY_SHORTCUT).value(primary), "="));
            }

            for (var object : collectionEntry.readValues(value)) {
                var createProcess = new HikariCreateProcess(object);
                createProcess.constants().cloneConstants(constants(), QueryConstant.PRIMARY_SHORTCUT);
                createProcess.run(collectionEntry, reference);
            }
            deleteProcess.run(collectionEntry, reference);
            return;
        }

        if (entry instanceof RepositoryMapEntry mapEntry) {
            // delete first
            var deleteProcess = new HikariDeleteProcess();




            return;
        }


        for (var child : entry.children()) {
            var value = Reflections.on(child.constants().constant(RepositoryConstant.PARAM_FIELD)).value(this.value);

            if (child instanceof RepositoryExternalEntry externalEntry) {
                var subprocess = new HikariUpdateProcess(value);
                subprocess.constants().put(QueryConstant.PRIMARY_SHORTCUT, QueryConstant.PrimaryShortCut.append(entry.primaries(), this.value));
                subprocess.run(externalEntry, reference);
                continue;
            }

            if (child.constants().has(RepositoryConstant.PRIMARY_KEY)) {
                // primaries cannot be updated
                continue;
            }

            if (child.constants().has(RepositoryConstant.VALUE_REFACTOR)) {
                value = child.constants().constant(RepositoryConstant.VALUE_REFACTOR).apply(value);
            }

            elements.add(child.id() + " = ?");
            arguments.add(value);
        }

        if (elements.isEmpty()) {
            return;
        }

        arguments.addAll(filters().stream().map(Filter::value).toList());
        reference.append(HikariFilterUtil.appendFiltering(UPDATE_VALUE.formatted(entry.id(), String.join(", ", elements)), filters()) + ";", arguments.toArray());
    }
}
