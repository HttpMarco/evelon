package net.bytemc.evelon.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytemc.evelon.local.LocalStorage;
import net.bytemc.evelon.sql.DatabaseStorage;
import net.bytemc.evelon.storages.Storage;

@Getter
@AllArgsConstructor
public enum RepositoryDepartureOrder {

    CHRONOLOGICAL(null),
    LOCAL(LocalStorage.class),
    DATABASE(DatabaseStorage.class);

    Class<? extends Storage> storage;

}
