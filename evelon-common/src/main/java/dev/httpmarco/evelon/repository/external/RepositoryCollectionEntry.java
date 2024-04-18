package dev.httpmarco.evelon.repository.external;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryFinder;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

@Getter
@Accessors(fluent = true)
public final class RepositoryCollectionEntry extends RepositoryExternalEntry {

    private final RepositoryEntry componentEntry;

    public RepositoryCollectionEntry(String id, @NotNull Field field, RepositoryExternalEntry parent) {
        super(id, field.getType(), parent);

        this.componentEntry = RepositoryEntryFinder.find(Evelon.generics(field)[0], null, field.getName() + "_value", this);

        if (this.componentEntry instanceof RepositoryExternalEntry externalEntry) {
            this.children().addAll(externalEntry.children());
            return;
        }
        this.children().add(componentEntry);
    }
}
