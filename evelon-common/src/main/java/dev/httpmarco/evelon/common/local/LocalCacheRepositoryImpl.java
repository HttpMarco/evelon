package dev.httpmarco.evelon.common.local;

import dev.httpmarco.evelon.common.filters.LayerFilterHandler;
import dev.httpmarco.evelon.common.layers.EvelonLayer;
import dev.httpmarco.evelon.common.query.SortedOrder;
import dev.httpmarco.evelon.common.query.DataQuery;
import dev.httpmarco.evelon.common.repository.RepositoryImpl;
import dev.httpmarco.osgan.reflections.Reflections;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@Getter
@Accessors(fluent = true)
public class LocalCacheRepositoryImpl<T> extends RepositoryImpl<T> implements LocalCacheRepository<T>, EvelonLayer<T> {

    private final String id = "LOCAL";
    private final LayerFilterHandler<?, ?> filterHandler = new LocalFilterHandler();
    private final List<LocalStorageEntry<T>> localData = new ArrayList<>();

    public LocalCacheRepositoryImpl(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public void create(DataQuery<T> query, T value) {
        localData.add(LocalStorageEntry.of(value));
    }

    @Override
    public void createIfNotExists(DataQuery<T> query, T value) {
        if (!this.exists(query)) {
            this.create(query, value);
        }
    }

    @Override
    public void deleteAll(DataQuery<T> query) {
        this.applyFilters(query).forEach(t -> localData().removeIf(entry -> entry.value().equals(t)));
    }

    @Override
    public void delete(DataQuery<T> query, T value) {
        localData().removeIf(entry -> entry.value().equals(value));
    }

    @Override
    public void update(DataQuery<T> query, T value) {
        // nothing to do because the same memory reference is used
    }

    @Override
    public void updateIf(DataQuery<T> query, T value, Predicate<T> predicate) {
        // nothing to do because the same memory reference is used
    }

    @Override
    public void upsert(DataQuery<T> query, T value) {
        // we can use the same method because the same memory reference is used and update not used
        this.createIfNotExists(query, value);
    }

    @Override
    public List<T> findAll(DataQuery<T> query) {
        return this.applyFilters(query).toList();
    }

    @Override
    public List<T> findAll(DataQuery<T> query, int limit) {
        return this.applyFilters(query).limit(limit).toList();
    }

    @Override
    public T find(DataQuery<T> query) {
        return this.applyFilters(query).findAny().orElse(null);
    }

    @Override
    public boolean exists(DataQuery<T> query) {
        return this.applyFilters(query).findAny().isPresent();
    }

    @Override
    public long count(DataQuery<T> query) {
        return this.applyFilters(query).count();
    }

    @Override
    public long sum(DataQuery<T> query, String id) {
        return this.findAll(query).stream().mapToLong(t -> (long) Reflections.getField(id, t)).sum();
    }

    @Override
    public double avg(DataQuery<T> query, String id) {
        return this.findAll(query).stream().mapToLong(t -> (long) Reflections.getField(id, t)).average().orElse(-1);
    }

    @Override
    public List<T> order(DataQuery<T> query, String id, int max, SortedOrder order) {
        return this.findAll(query).stream().sorted((o1, o2) -> {
            var value1 = (long) Reflections.getField(id, o1);
            var value2 = (long) Reflections.getField(id, o2);
            return order == SortedOrder.ASCENDING ? Long.compare(value1, value2) : Long.compare(value2, value1);
        }).toList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> List<E> collect(DataQuery<T> query, String id, Class<E> clazz) {
        return findAll(query).stream().map(it -> (E) Reflections.getField(id, it)).toList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> List<E> collect(DataQuery<T> query, String id, int limit, Class<E> clazz) {
        return findAll(query, limit).stream().map(it -> (E) Reflections.getField(id, it)).toList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <E> E collectSingle(DataQuery<T> query, String id, Class<E> clazz) {
        return (E) Reflections.getField(id, find(query));
    }

    @Override
    public T max(DataQuery<T> query, String id) {
        return this.findAll(query).stream().max(Comparator.comparingLong(o -> (long) Reflections.getField(id, o))).orElse(null);
    }

    @Override
    public T min(DataQuery<T> query, String id) {
        return this.findAll(query).stream().min(Comparator.comparingLong(o -> (long) Reflections.getField(id, o))).orElse(null);
    }

    private Stream<T> applyFilters(DataQuery<T> query) {
        return localData().stream().map(LocalStorageEntry::value);
    }

}
