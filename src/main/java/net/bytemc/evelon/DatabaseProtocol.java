package net.bytemc.evelon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.bytemc.evelon.mongo.MongoStorage;
import net.bytemc.evelon.sql.SQLStorage;

@Getter
@AllArgsConstructor
public enum DatabaseProtocol {

    MARIADB(SQLStorage.class),
    MONGODB(MongoStorage.class);

    private Class<? extends Storage> storageClass;

    //TODO
    // MySQL
    // POSTGRESQL

}
