package net.bytemc.evelon.mongo.misc;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.bytemc.evelon.mongo.MongoConnection;
import net.bytemc.evelon.repository.Filter;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryQuery;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Collection;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoHelper {

    public static <T> MongoCollection<T> getCollection(@NonNull Repository<T> repository) {
        return MongoConnection.getDatabase().getCollection(repository.getName(), repository.repositoryClass().clazz());
    }

    public static Bson linkFilters(Collection<Filter> filters) {
        return Filters.and(filters.stream().map(Filter::mongoFilter).toList());
    }

    public static <T> FindIterable<T> query(RepositoryQuery<T> query, int limit) {
        FindIterable<T> iterable = getCollection(query.getRepository())
            .find(MongoHelper.linkFilters(query.getFilters()));
        if (limit > 0) {
            iterable.limit(limit);
        }
        return iterable;
    }

}
