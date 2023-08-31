package net.bytemc.evelon.repository.properties;

import net.bytemc.evelon.repository.Filter;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryQuery;

class SyncAllWithFiltersStartupProperty<T> implements StartupProperties<T> {
    private final Filter[] filters;

    SyncAllWithFiltersStartupProperty(Filter... filters) {
        this.filters = filters;
    }

    @Override
    public void initialize(Repository<T> repository) {
        RepositoryQuery<T> query = repository.query();

        for (Filter filter : this.filters) {
            query.filter(filter);
        }

        query.database().findAll().forEach(t -> repository.query().cache(t));
    }
}
