package dev.httpmarco.evelon.common.local;

import dev.httpmarco.evelon.common.layers.RepositoryLayer;
import dev.httpmarco.evelon.common.misc.Reflections;
import dev.httpmarco.evelon.common.misc.SortedOrder;
import dev.httpmarco.evelon.common.model.EmptyModel;
import dev.httpmarco.evelon.common.query.DataQuery;
import dev.httpmarco.evelon.common.repository.local.LocalStorageEntry;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LocalStorageLayer extends RepositoryLayer {

    public LocalStorageLayer() {
        super(new LocalFilterHandler(), new EmptyModel());
    }

    @Override
    public <T> void create(DataQuery<T> query, T value) {
        query.getRepository().localData().add(LocalStorageEntry.of(value));
    }

    @Override
    public <T> void createIfNotExists(DataQuery<T> query, T value) {
        if (!this.exists(query)) {
            this.create(query, value);
        }
    }

    @Override
    public <T> void deleteAll(DataQuery<T> query) {
        this.applyFilters(query).forEach(t -> query.getRepository().localData().removeIf(entry -> entry.value().equals(t)));
    }

    @Override
    public <T> void delete(DataQuery<T> query, T value) {
        query.getRepository().localData().removeIf(entry -> entry.value().equals(value));
    }

    @Override
    public <T> void update(DataQuery<T> query, T value) {
        // nothing to do because the same memory reference is used
    }

    @Override
    public <T> void updateIf(DataQuery<T> query, T value, Predicate<T> predicate) {
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
    public <T> List<T> findAll(DataQuery<T> query, int limit) {
        return this.applyFilters(query).limit(limit).toList();
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
        return this.findAll(query).stream().mapToLong(t -> (long) Reflections.getFieldValue(id, t)).sum();
    }

    @Override
    public <T> double avg(DataQuery<T> query, String id) {
        return this.findAll(query).stream().mapToLong(t -> (long) Reflections.getFieldValue(id, t)).average().orElse(-1);
    }

    @Override
    public <T> List<T> order(DataQuery<T> query, String id, int max, SortedOrder order) {
        return this.findAll(query).stream().sorted((o1, o2) -> {
            var value1 = (long) Reflections.getFieldValue(id, o1);
            var value2 = (long) Reflections.getFieldValue(id, o2);
            return order == SortedOrder.ASCENDING ? Long.compare(value1, value2) : Long.compare(value2, value1);
        }).toList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E, T> List<E> collect(DataQuery<T> query, String id, Class<E> clazz) {
        return findAll(query).stream().map(it -> (E) Reflections.getFieldValue(id, it)).toList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E, T> List<E> collect(DataQuery<T> query, String id, int limit, Class<E> clazz) {
        return findAll(query, limit).stream().map(it -> (E) Reflections.getFieldValue(id, it)).toList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E, T> E collectSingle(DataQuery<T> query, String id, Class<E> clazz) {
        return (E) Reflections.getFieldValue(id, find(query));
    }

    @Override
    public <T> T max(DataQuery<T> query, String id) {
        return this.findAll(query).stream().max(Comparator.comparingLong(o -> (long) Reflections.getFieldValue(id, o))).orElse(null);
    }

    @Override
    public <T> T min(DataQuery<T> query, String id) {
        return this.findAll(query).stream().min(Comparator.comparingLong(o -> (long) Reflections.getFieldValue(id, o))).orElse(null);
    }

    private <T> Stream<T> applyFilters(DataQuery<T> query) {
        return query.getRepository().localData().stream().map(LocalStorageEntry::value);
    }
}