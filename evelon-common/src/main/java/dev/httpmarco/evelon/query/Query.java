package dev.httpmarco.evelon.query;

import dev.httpmarco.evelon.Ordering;
import dev.httpmarco.evelon.Repository;
import dev.httpmarco.evelon.constant.ConstantPool;
import dev.httpmarco.evelon.filtering.Filter;
import dev.httpmarco.evelon.layer.Layer;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Accessors(fluent = true)
public class Query<V> {

    @Getter
    private final Repository<V> associatedRepository;
    @Getter
    private final Map<Layer<?>, List<Filter<?, ?>>> filters = new HashMap<>();
    @Getter
    private final ConstantPool constants = new ConstantPool();
    private final Layer<?>[] usedLayers;

    public Query<V> limit(int limit) {
        this.constants.put(QueryConstant.LIMIT, 1);
        return this;
    }

    public Query(Repository<V> repository, @NotNull Set<Layer<?>> usedLayers) {
        this.associatedRepository = repository;
        this.usedLayers = usedLayers.toArray(Layer[]::new);

        for (var layer : this.usedLayers) {
            this.filters.put(layer, new LinkedList<>());
        }
    }

    public Query<V> match(String id, Object value) {
        for (var layer : usedLayers) {
            filter(layer.filterHandler().match(id, value), layer);
        }
        return this;
    }

    public Query<V> like(String id, String value) {
        for (var layer : usedLayers) {
            filter(layer.filterHandler().like(id, value), layer);
        }
        return this;
    }

    public Query<V> noneMatch(String id, Object value) {
        for (var layer : usedLayers) {
            filter(layer.filterHandler().noneMatch(id, value), layer);
        }
        return this;
    }

    private void filter(Filter<?, ?> filter, Layer<?> layer) {
        var list = filters.get(layer);
        list.add(filter);
        this.filters.put(layer, list);
    }


    public void create(V value) {
        for (var layer : this.usedLayers) {
            layer.queryMethod(associatedRepository).create(this, value);
        }
    }

    public void update(V value) {
        for (var layer : this.usedLayers) {
            layer.queryMethod(associatedRepository).update(this, value);
        }
    }

    public void delete() {
        for (var layer : usedLayers) {
            layer.queryMethod(associatedRepository).delete(this);
        }
    }

    public boolean exists() {
        for (var layer : usedLayers) {
            if (layer.queryMethod(associatedRepository).exists(this)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public V findFirst() {
        constants().put(QueryConstant.LIMIT, 1);
        for (var layer : usedLayers) {
            var value = layer.queryMethod(associatedRepository).findFirst(this);
            if (value != null) {
                return (V) value;
            }
        }
        return null;
    }

    public long sum(String id) {
        long sum = 0;
        for (var layer : usedLayers) {
            sum += layer.queryMethod(associatedRepository).sum(this, id);
        }
        return sum;
    }

    public double average(String id) {
        double average = 0;
        for (var layer : usedLayers) {
            average += (long) layer.queryMethod(associatedRepository).average(this, id);
        }
        return average;
    }

    @SuppressWarnings("unchecked")
    public List<V> order(String id, Ordering ordering) {
        var elements = new ArrayList<V>();
        for (var layer : usedLayers) {
            elements.addAll((Collection<? extends V>) layer.queryMethod(associatedRepository).order(this, id, ordering));
        }
        return elements;
    }


    public long count() {
        int count = 0;
        for (var layer : usedLayers) {
            count += (int) layer.queryMethod(associatedRepository).count(this);
        }
        return count;
    }

    @SuppressWarnings("unchecked")
    public List<V> find() {
        var elements = new ArrayList<V>();
        for (var layer : usedLayers) {
            elements.addAll((Collection<? extends V>) layer.queryMethod(associatedRepository).find(this));
        }
        return elements;
    }
}
