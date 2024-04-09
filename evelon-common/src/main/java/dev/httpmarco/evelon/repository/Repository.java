package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.repository.entries.RepositoryObjectEntry;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Repository<T> {

    private final RepositoryObjectEntry entry;

}
