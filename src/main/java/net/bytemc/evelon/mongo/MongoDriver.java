package net.bytemc.evelon.mongo;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.ConnectionString;
import com.mongodb.Function;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.event.CommandListener;
import com.mongodb.event.CommandStartedEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytemc.evelon.Debugger;
import net.bytemc.evelon.Evelon;
import net.bytemc.evelon.cradinates.DatabaseCradinates;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.annotations.Ignore;
import org.bson.Document;
import org.bson.UuidRepresentation;
import org.bson.codecs.JsonObjectCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.json.JsonObject;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MongoDriver implements AutoCloseable {

    private static final Function<DatabaseCradinates, ConnectionString> CONNECTION_STRING = cradinates -> new ConnectionString(String.format(
        "mongodb://%s:%s@%s:%s/%s",
        cradinates.user(), cradinates.password(), cradinates.hostname(), cradinates.port(), cradinates.database()
    ));
    protected static final Gson GSON = new GsonBuilder()
        .serializeNulls()
        .setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                return fieldAttributes.getAnnotation(Ignore.class) != null;
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return false;
            }
        })
        .create();

    protected MongoClient client;
    protected MongoDatabase database;

    @ApiStatus.Internal
    protected void init() {
        final var cradinates = Evelon.getCradinates();
        if (cradinates == null) {
            throw new IllegalStateException("Cradinates are not set");
        }
        final var codecRegistry = CodecRegistries.fromProviders(
            CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry()),
            new JsonObjectCodecProvider()
        );
        final var settings = MongoClientSettings.builder()
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
        client = MongoClients.create(settings);
        database = client.getDatabase(cradinates.database());
        Debugger.log("Established connection to mongodb server");
    }

    public MongoCollection<Document> getCollection(Repository<?> repository) {
        return database.getCollection(repository.getName());
    }

    public MongoCollection<JsonObject> getJsonCollection(Repository<?> repository) {
        return database.getCollection(repository.getName(), JsonObject.class);
    }

    public MongoCollection<Document> getCollection(String repository) {
        return database.getCollection(repository);
    }

    public MongoCollection<JsonObject> getJsonCollection(String repository) {
        return database.getCollection(repository, JsonObject.class);
    }

    @Override
    public void close() throws Exception {
        if (client != null) {
            client.close();
        }
    }
}
