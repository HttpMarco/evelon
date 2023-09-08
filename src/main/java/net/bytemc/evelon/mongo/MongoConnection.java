package net.bytemc.evelon.mongo;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.mongo.codec.EvelonCodecProvider;
import net.bytemc.evelon.mongo.codec.ObjectMapperFactory;
import net.bytemc.evelon.repository.Repository;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.jetbrains.annotations.ApiStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoConnection {

    private static final String CONNECTION_STRING = "mongodb://%s:%s@%s:%o/%s";

    @Getter
    private static MongoDatabase database;

    @ApiStatus.Internal
    public static void init() {
        DatabaseCradinates cradinates = Evelon.getDatabaseCradinates();
        /*database = MongoClients.create(CONNECTION_STRING.formatted(
            cradinates.user(),
            cradinates.password(),
            cradinates.hostname(),
            cradinates.port(),
            cradinates.database()
        )).getDatabase(cradinates.database());*/

        // TODO: Only for local testing reasons
        final CodecRegistry codecRegistry = CodecRegistries.fromProviders(
            CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry()),
            new EvelonCodecProvider(ObjectMapperFactory.create())
        );
        database = MongoClients.create().getDatabase(cradinates.database()).withCodecRegistry(codecRegistry);
        Debugger.log("Established connection to mongodb server");
    }

    public static MongoCollection<Document> getCollection(Repository<?> repository) {
        return database.getCollection(repository.getName());
    }

}
