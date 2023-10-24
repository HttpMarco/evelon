package net.bytemc.evelon.mongo;

import lombok.SneakyThrows;
import net.bytemc.evelon.Storage;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.mongo.misc.MongoHelper;
import net.bytemc.evelon.repository.RepositoryHelper;
import net.bytemc.evelon.repository.RepositoryQuery;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static net.bytemc.evelon.mongo.misc.MongoHelper.*;

public final class MongoStorage implements Storage {

    public MongoStorage() {
        MongoConnection.init();
    }

    @Override
    public <T> void create(RepositoryQuery<T> query, T value) {
        getCollection(query.getRepository()).insertOne(value);
    }

    @Override
    public <T> void delete(RepositoryQuery<T> query) {
        if (query.getFilters().isEmpty()) {
            throw new UnsupportedOperationException("You have to define a primary key, or use filters to do a mongo update");
        }
        getCollection(query.getRepository()).findOneAndDelete(linkFilters(query.getFilters()));
    }

    @Override
    public <T> List<T> findAll(RepositoryQuery<T> query) {
        return query(query, -1).into(new ArrayList<>());
    }

    @Override
    public <T> @Nullable T findFirst(RepositoryQuery<T> query) {
        return query(query, 1).first();
    }

    @SneakyThrows
    @Override
    public <T> void update(RepositoryQuery<T> query, T value) {
        final Bson filter = MongoHelper.filtersOrPrimaries(query.getFilters(), query.getRepository(), value)
            .orElseThrow((Supplier<Throwable>) () ->
                new UnsupportedOperationException("You have to define a primary key, or use filters to do a mongo update")
            );

        final Document update = new Document();
        for (Field row : query.getRepository().repositoryClass().getRows()) {
            update.append(RepositoryHelper.getFieldName(row), Reflections.readField(value, row));
        }
        getCollection(query.getRepository()).updateOne(filter, new Document("$set", update));
    }

    @Override
    public <T> boolean exists(RepositoryQuery<T> query) {
        return findFirst(query) != null;
    }

    @Override
    public <T> long count(RepositoryQuery<T> query) {
        return getCollection(query.getRepository()).countDocuments(linkFilters(query.getFilters()));
    }

    @Override
    public <T> long sum(RepositoryQuery<T> query, String id) {
        // todo
        return 0;
    }

    @Override
    public <T> double avg(RepositoryQuery<T> query, String id) {
        //todo
        return 0;
    }
}
