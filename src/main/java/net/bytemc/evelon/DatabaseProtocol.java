package net.bytemc.evelon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytemc.evelon.h2.H2DatabaseStorage;
import net.bytemc.evelon.maria.MariaDBStorage;
import net.bytemc.evelon.mongo.MongoStorage;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum DatabaseProtocol {

    MARIADB(MariaDBStorage.class, "org.mariadb.jdbc.Driver"),
    H2(H2DatabaseStorage.class, "org.h2.Driver"),

    MONGODB(MongoStorage.class);

    private final Class<? extends Storage> storageClass;
    private String driver;

    //TODO
    // MySQL
    // POSTGRESQL

}
