package net.bytemc.evelon.mongo;

import net.bytemc.evelon.Storage;
import net.bytemc.evelon.repository.RepositoryQuery;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.bytemc.evelon.mongo.misc.MongoHelper.*;

public final class MongoStorage implements Storage {

    public MongoStorage() {
        MongoConnection.init();
    }

    @Override
    public <T> void create(RepositoryQuery<T> query, T value) {
        getCollection(null, query.getRepository()).insertOne(value);
    }

    @Override
    public <T> void delete(RepositoryQuery<T> query) {
        getCollection(null, query.getRepository())
            .findOneAndDelete(linkFilters(query.getFilters()));
    }

    @Override
    public <T> List<T> findAll(RepositoryQuery<T> query) {
        return query(query, -1).into(new ArrayList<>());
    }

    @Override
    public <T> @Nullable T findFirst(RepositoryQuery<T> query) {
        return query(query, 1).first();
    }

    @Override
    public <T> void update(RepositoryQuery<T> query, T value) {
        getCollection(null, query.getRepository())
            .findOneAndReplace(linkFilters(query.getFilters()), value);
    }

    @Override
    public <T> boolean exists(RepositoryQuery<T> query) {
        return findFirst(query) != null;
    }

    @Override
    public <T> long count(RepositoryQuery<T> query) {
        return getCollection(null, query.getRepository())
            .countDocuments(linkFilters(query.getFilters()));
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
