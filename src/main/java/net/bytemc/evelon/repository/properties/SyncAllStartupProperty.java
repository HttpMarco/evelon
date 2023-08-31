package net.bytemc.evelon.repository.properties;

import net.bytemc.evelon.repository.Repository;

class SyncAllStartupProperty<T> implements StartupProperties<T> {
    @Override
    public void initialize(Repository<T> repository) {
        repository.query().database().findAll().forEach(t -> repository.query().cache(t));
    }
}
