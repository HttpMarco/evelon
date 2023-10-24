package net.bytemc.evelon.mongo.misc;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.bytemc.evelon.misc.Reflections;
import net.bytemc.evelon.mongo.MongoConnection;
import net.bytemc.evelon.repository.Filter;
import net.bytemc.evelon.repository.Repository;
import net.bytemc.evelon.repository.RepositoryHelper;
import net.bytemc.evelon.repository.RepositoryQuery;
import org.bson.conversions.Bson;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoHelper {

    public static <T> MongoCollection<T> getCollection(@NonNull Repository<T> repository) {
        return MongoConnection.getDatabase().getCollection(repository.getName(), repository.repositoryClass().clazz());
    }

    public static Bson linkFilters(Collection<Filter> filters) {
        return Filters.and(filters.stream().map(Filter::mongoFilter).toList());
    }

    public static Optional<Bson> filtersOrPrimaries(Collection<Filter> filters, Repository<?> repository, Object value) {
        if (filters.isEmpty()) {
            if (!repository.repositoryClass().hasPrimary()) {
                return Optional.empty();
            }
            for (Field primary : repository.repositoryClass().getPrimaries()) {
                filters.add(Filter.match(
                    RepositoryHelper.getFieldName(primary),
                    Reflections.readField(value, primary))
                );
            }
        }
        return Optional.of(linkFilters(filters));
    }

    public static <T> FindIterable<T> query(RepositoryQuery<T> query, int limit) {
        final MongoCollection<T> collection = getCollection(query.getRepository());
        FindIterable<T> iterable;
        if (!query.getFilters().isEmpty()) {
            iterable = collection.find(MongoHelper.linkFilters(query.getFilters()));
        } else {
            iterable = collection.find();
        }
        if (limit > 0) {
            iterable.limit(limit);
        }
        iterable.projection(Projections.exclude("_id"));
        return iterable;
    }

}
