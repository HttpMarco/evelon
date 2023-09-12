package net.bytemc.evelon.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.Function;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.event.CommandFailedEvent;
import com.mongodb.event.CommandListener;
import com.mongodb.event.CommandStartedEvent;
import com.mongodb.event.CommandSucceededEvent;
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
import org.bson.UuidRepresentation;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoConnection {

    private static final Function<DatabaseCradinates, ConnectionString> CONNECTION_STRING = cradinates -> new ConnectionString(String.format(
        "mongodb://%s:%s@%s:%s/%s",
        cradinates.user(), cradinates.password(), cradinates.hostname(), cradinates.port(), cradinates.database()
    ));

    @Getter
    private static MongoDatabase database;

    @ApiStatus.Internal
    public static void init() {
        final DatabaseCradinates cradinates = Evelon.getDatabaseCradinates();
        final CodecRegistry codecRegistry = CodecRegistries.fromProviders(
            CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry()),
            new EvelonCodecProvider(ObjectMapperFactory.create())
        );
        final MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(CONNECTION_STRING.apply(cradinates))
            .uuidRepresentation(UuidRepresentation.JAVA_LEGACY)
            .codecRegistry(codecRegistry)
            .addCommandListener(new CommandListener() {
                @Override
                public void commandStarted(@NotNull CommandStartedEvent event) {
                    Debugger.log(event.getCommand().toJson());
                }
            })
            .build();
        database = MongoClients.create(settings).getDatabase(cradinates.database());
        Debugger.log("Established connection to mongodb server");
    }

    public static MongoCollection<Document> getCollection(Repository<?> repository) {
        return database.getCollection(repository.getName());
    }

}
