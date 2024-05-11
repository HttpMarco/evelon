package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.external.RepositoryCollectionEntry;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
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

        if(entry instanceof RepositoryCollectionEntry collectionEntry) {
            // delete
            //todo delete existing sql entries and create new
            return;
        }


        for (var child : entry.children()) {
            if (child instanceof RepositoryExternalEntry externalEntry) {
                this.run(externalEntry, reference);
                continue;
            }

            if(child.constants().has(RepositoryConstant.PRIMARY_KEY)) {
                // primaries cannot be updated
                continue;
            }

            var value =  Reflections.on(child.constants().constant(RepositoryConstant.PARAM_FIELD)).value(this.value);

            if (child.constants().has(RepositoryConstant.VALUE_REFACTOR)) {
                value = child.constants().constant(RepositoryConstant.VALUE_REFACTOR).apply(value);
            }

            elements.add(child.id() + " = ?");
            arguments.add(value);
        }

        if (elements.isEmpty()) {
            return;
        }

        reference.append(HikariFilterUtil.appendFiltering(UPDATE_VALUE.formatted(entry.id(), String.join(", ", elements)), filters()) + ";", arguments.toArray());
    }
}
