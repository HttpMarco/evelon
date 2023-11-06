package net.bytemc.evelon.mongo;

import com.mongodb.Function;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.UpdateOptions;
import lombok.SneakyThrows;
import net.bytemc.evelon.Storage;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.misc.SortedOrder;
import net.bytemc.evelon.mongo.misc.MongoHelper;
import net.bytemc.evelon.repository.RepositoryQuery;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.bytemc.evelon.mongo.misc.MongoHelper.linkFilters;

public final class MongoStorage extends MongoDriver implements Storage {

    private static final UpdateOptions UPSERT_OPTIONS = new UpdateOptions().upsert(true);

    public MongoStorage() {
        init();
    }

    @Override
    public <T> void create(RepositoryQuery<T> query, T value) {
        getJsonCollection(query.getRepository()).insertOne(new JsonObject(GSON.toJson(value)));
    }

    @Override
    public <T> void delete(RepositoryQuery<T> query) {
        if (query.getFilters().isEmpty()) {
            throw new UnsupportedOperationException("You have to define filters to do a mongo deletion");
        }
        getJsonCollection(query.getRepository()).deleteMany(linkFilters(query.getFilters()));
    }

    @Override
    public <T> List<T> findAll(RepositoryQuery<T> query) {
        return query(query)
            .map(jsonObject -> GSON.fromJson(jsonObject.getJson(), query.getRepository().repositoryClass().clazz()))
            .into(new ArrayList<>());
    }

    @Override
    public <T> @Nullable T findFirst(RepositoryQuery<T> query) {
        if (query.getFilters().isEmpty()) {
            throw new UnsupportedOperationException("You have to define filters to do a mongo find");
        }
        return query(query)
            .limit(1)
            .map(jsonObject -> GSON.fromJson(jsonObject.getJson(), query.getRepository().repositoryClass().clazz()))
            .first();
    }

    @SneakyThrows
    @Override
    public <T> void update(RepositoryQuery<T> query, T value) {
        update(query, value, null);
    }

    @Override
    public <T> void upsert(RepositoryQuery<T> query, T value) {
        update(query, value, UPSERT_OPTIONS);
    }

    @Override
    public <T> boolean exists(RepositoryQuery<T> query) {
        return findFirst(query) != null;
    }

    @Override
    public <T> long count(RepositoryQuery<T> query) {
        if (query.getFilters().isEmpty()) {
            return getCollection(query.getRepository()).countDocuments();
        }
        return getCollection(query.getRepository()).countDocuments(linkFilters(query.getFilters()));
    }

    @Override
    public <T> long sum(RepositoryQuery<T> query, String id) {
        return calculate(query, id,
            Accumulators.sum("total", "$" + id),
            document -> document.getInteger("total").longValue(),
            0L
        );
    }

    @Override
    public <T> double avg(RepositoryQuery<T> query, String id) {
        return calculate(query, id,
            Accumulators.avg("average", "$" + id),
            document -> document.getDouble("average"),
            0.0D
        );
    }

    @Override
    public <T> List<T> order(RepositoryQuery<T> query, String id, int max, SortedOrder order) {
        return query(query)
            .limit(max)
            .sort(order.toMongo(id))
            .map(jsonObject -> GSON.fromJson(jsonObject.getJson(), query.getRepository().repositoryClass().clazz()))
            .into(new ArrayList<>());
    }

    private <T> FindIterable<JsonObject> query(RepositoryQuery<T> query) {
        var collection = getJsonCollection(query.getRepository());
        FindIterable<JsonObject> iterable;
        if (!query.getFilters().isEmpty()) {
            iterable = collection.find(linkFilters(query.getFilters()));
        } else {
            iterable = collection.find();
        }
        return iterable;
    }

    private <T> void update(RepositoryQuery<T> query, T value, UpdateOptions options) {
        var filters = MongoHelper.filtersOrPrimaries(query.getFilters(), query.getRepository(), value).orElseThrow(
            () -> new UnsupportedOperationException("You have to define primary-keys or filters to do a mongo update")
        );
        var document = Document.parse(GSON.toJson(value));
        var update = new Document("$set", document);
        var collection = getJsonCollection(query.getRepository());
        if (options == null) {
            collection.updateMany(filters, update);
        } else {
            collection.updateMany(filters, update, options);
        }
    }

    private <T> T calculate(
        RepositoryQuery<?> query,
        String id,
        BsonField pipelineAction,
        Function<Document, T> map,
        T def
    ) {
        var field = Reflections.getOptionalField(query.getRepository().repositoryClass().clazz(), id);
        var repositoryClass = query.getRepository().repositoryClass().clazz();
        if (field.isEmpty()) {
            throw new UnsupportedOperationException(
                "Cannot find field '" + id + "' in repository-class '" + repositoryClass.getName() + "'"
            );
        }
        if (!Reflections.isNumber(field.get().getType())) {
            throw new UnsupportedOperationException(
                "Field '" + id + "' in repository-class '" + repositoryClass.getName() + "' is not a number"
            );
        }

        var collection = getCollection(query.getRepository());
        var pipeline = new ArrayList<Bson>(2);
        if (!query.getFilters().isEmpty()) {
            pipeline.add(Aggregates.match(linkFilters(query.getFilters())));
        }
        pipeline.add(Aggregates.group(null, pipelineAction));

        var result = collection.aggregate(pipeline);
        try (var iterator = result.iterator()) {
            if (iterator.hasNext()) {
                var document = iterator.next();
                return map.apply(document);
            }
        }
        return def;
    }
}
