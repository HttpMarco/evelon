package net.bytemc.evelon.repository;

import lombok.AllArgsConstructor;
import net.bytemc.evelon.repository.filters.*;

@AllArgsConstructor
public final class RepositoryFilterQuery<T> {

    private final RepositoryQuery<T> repositoryQuery;

    public RepositoryFilterQuery min(String id, Number min) {
        this.repositoryQuery.getFilters().add(new MinimumFilter(id, min));
        return this;
    }

    public RepositoryFilterQuery max(String id, Number max) {
        this.repositoryQuery.getFilters().add(new MaximumFilter(id, max));
        return this;
    }

    public RepositoryFilterQuery between(String id, Number min, Number max) {
        this.repositoryQuery.getFilters().add(new BetweenFilter(id, min, max));
        return this;
    }

    public RepositoryFilterQuery match(String id, Object object) {
        this.repositoryQuery.getFilters().add(new MatchFilter(id, object));
        return this;
    }

    public RepositoryFilterQuery noneMatch(String id, Object object) {
        this.repositoryQuery.getFilters().add(new NoneMatchFilter(id, object));
        return this;
    }

    public RepositoryQuery<T> complete() {
        return this.repositoryQuery;
    }
}