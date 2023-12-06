package net.bytemc.evelon.local;

import net.bytemc.evelon.exceptions.RequiredFieldTypeNotPresentException;
import net.bytemc.evelon.layers.RepositoryLayer;
import net.bytemc.evelon.misc.EvelonReflections;
import net.bytemc.evelon.misc.SortedOrder;
import net.bytemc.evelon.query.DataQuery;
import net.bytemc.evelon.repository.Repository;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public final class LocalStorageLayer implements RepositoryLayer {

    @Override
    public <T> void create(@NotNull DataQuery<T> query, T value) {
        query.getRepository().localStorage().add(LocalStorageEntry.of(value));
    }

    @Override
    public <T> void createIfNotExists(DataQuery<T> query, T value) {
        if (!this.exists(query)) {
            this.create(query, value);
        }
    }

    @Override
    public <T> void deleteAll(DataQuery<T> query) {
        this.applyFilters(query).forEach(t -> query.getRepository().localStorage().removeIf(entry -> entry.value().equals(t)));
    }

    @Override
    public <T> void delete(DataQuery<T> query, T value) {
        query.getRepository().localStorage().remove(value);
    }

    @Override
    public <T> void update(DataQuery<T> query, T value) {
        // nothing to do because the same memory reference is used
    }

    @Override
    public <T> void upsert(DataQuery<T> query, T value) {
        // we can use the same method because the same memory reference is used and update not used
        this.createIfNotExists(query, value);
    }

    @Override
    public <T> List<T> findAll(DataQuery<T> query) {
        return this.applyFilters(query).toList();
    }

    @Override
    public <T> T find(DataQuery<T> query) {
        return this.applyFilters(query).findAny().orElse(null);
    }

    @Override
    public <T> boolean exists(DataQuery<T> query) {
        return this.applyFilters(query).findAny().isPresent();
    }

    @Override
    public <T> long count(DataQuery<T> query) {
        return this.applyFilters(query).count();
    }

    @Override
    public <T> long sum(DataQuery<T> query, String id) {
        return this.findAll(query).stream().mapToLong(t -> (long) EvelonReflections.getFieldValue(id, t)).sum();
    }

    @Override
    public <T> double avg(DataQuery<T> query, String id) {
        return this.findAll(query).stream().mapToLong(t -> (long) EvelonReflections.getFieldValue(id, t)).average().orElse(-1);
    }

    @Override
    public <T> List<T> order(DataQuery<T> query, String id, int max, SortedOrder order) {
        return this.findAll(query).stream().sorted((o1, o2) -> {
            var value1 = (long) EvelonReflections.getFieldValue(id, o1);
            var value2 = (long) EvelonReflections.getFieldValue(id, o2);
            if (order == SortedOrder.ASCENDING) {
                return Long.compare(value1, value2);
            } else {
                return Long.compare(value2, value1);
            }
        }).toList();
    }

    private <T> Stream<T> applyFilters(DataQuery<T> query) {
        return query.getRepository().localStorage().stream().map(LocalStorageEntry::value);
    }
}
