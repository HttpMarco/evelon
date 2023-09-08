package net.bytemc.evelon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytemc.evelon.h2.H2DatabaseStorage;
import net.bytemc.evelon.maria.MariaDBStorage;
import net.bytemc.evelon.mongo.MongoStorage;
import net.bytemc.evelon.sql.SQLStorage;
import org.h2.Driver;

@Getter
@AllArgsConstructor
public enum DatabaseProtocol {

    MARIADB(MariaDBStorage.class, "org.mariadb.jdbc.Driver"),
    H2(H2DatabaseStorage.class, "org.h2.Driver"),

    MONGODB(MongoStorage.class);

    private final Class<? extends Storage> storageClass;
    private String driver;

     DatabaseProtocol(Class<? extends Storage> storageClass) {
        this.storageClass = storageClass;
    }

    //TODO
    // MySQL
    // POSTGRESQL

}
