package dev.httpmarco.evelon.sql.parent.process;

import dev.httpmarco.evelon.RepositoryConstant;
import dev.httpmarco.evelon.RepositoryExternalEntry;
import dev.httpmarco.evelon.process.kind.UpdateProcess;
import dev.httpmarco.evelon.sql.parent.reference.HikariProcessReference;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@AllArgsConstructor
public final class HikariUpdateProcess extends UpdateProcess<HikariProcessReference> {

    private static final String UPDATE_VALUE = "UPDATE %s SET %s;";
    private Object value;

    @Override
    public void run(@NotNull RepositoryExternalEntry entry, @NotNull HikariProcessReference reference) {
        var elements = new ArrayList<String>();

        for (var child : entry.children()) {
            if(child instanceof RepositoryExternalEntry externalEntry) {
                this.run(externalEntry, reference);
                continue;
            }

            var value = Reflections.on(this.value).value(child.id());

            if (child.hasConstant(RepositoryConstant.VALUE_REWRITING)) {
                value = child.constant(RepositoryConstant.VALUE_REWRITING).apply(value);
            }

            elements.add(child.id() + " = '" + value + "'");
        }

        if(elements.isEmpty()) {
            return;
        }
        reference.append(UPDATE_VALUE.formatted(entry.id(), String.join(", ", elements)));
    }
}
