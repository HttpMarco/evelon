package dev.httpmarco.evelon.repository.external;

import dev.httpmarco.evelon.repository.RepositoryEntry;
import dev.httpmarco.evelon.repository.RepositoryEntryFinder;
import dev.httpmarco.evelon.repository.RepositoryExternalEntry;
import dev.httpmarco.evelon.repository.RepositoryValueCollector;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

@Getter
@Accessors(fluent = true)
public final class RepositoryCollectionEntry extends RepositoryExternalEntry implements RepositoryValueCollector {

    private final RepositoryEntry componentEntry;

    public RepositoryCollectionEntry(String id, @NotNull Field field, RepositoryExternalEntry parent) {
        super(id, field.getType(), parent);

        var collectionType = Reflections.on(field).generic(0);
        this.componentEntry = RepositoryEntryFinder.find(collectionType, field, field.getName(), this);

        if (this.componentEntry instanceof RepositoryExternalEntry externalEntry) {
            this.children().addAll(externalEntry.children());
            return;
        }

        this.children().add(componentEntry);
    }
}
