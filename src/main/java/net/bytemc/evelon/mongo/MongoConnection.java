package net.bytemc.evelon.mongo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.repository.Repository;
import org.jetbrains.annotations.ApiStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoConnection {

    private static final String CONNECTION_STRING = "mongodb://%s:%s@%s:%o/%s";

    private static MongoDatabase DATABASE;

    @ApiStatus.Internal
    public static void init() {
        DatabaseCradinates cradinates = Evelon.getDatabaseCradinates();
        DATABASE = MongoClients.create(CONNECTION_STRING.formatted(
            cradinates.user(),
            cradinates.password(),
            cradinates.hostname(),
            cradinates.port(),
            cradinates.database()
        )).getDatabase(cradinates.database());
        Debugger.log("Established connection to mongodb server");
    }

    public static <T> MongoCollection<T> getCollection(Repository<T> repository) {
        return DATABASE.getCollection(repository.getName(), repository.repositoryClass().clazz());
    }

}
