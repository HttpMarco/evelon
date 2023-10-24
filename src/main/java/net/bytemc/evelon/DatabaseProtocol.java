package net.bytemc.evelon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytemc.evelon.h2.H2DatabaseStorage;
import net.bytemc.evelon.maria.MariaDBStorage;
import net.bytemc.evelon.mongo.MongoStorage;
import net.bytemc.evelon.mysql.MysqlDBStorage;

@Getter
@AllArgsConstructor
@RequiredArgsConstructor
public enum DatabaseProtocol {

    MARIADB(MariaDBStorage.class, "org.mariadb.jdbc.Driver"),
    H2(H2DatabaseStorage.class, "org.h2.Driver"),
    MYSQL(MysqlDBStorage.class, "com.mysql.cj.jdbc.Driver"),
    MONGODB(MongoStorage.class);

    private final Class<? extends Storage> storageClass;
    private String driver;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

    //TODO
    // POSTGRESQL

}
