package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.constant.ConstantPool;
import dev.httpmarco.evelon.filtering.Filter;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@Getter
@Accessors(fluent = true)
public abstract class Process<F extends Filter<?, ?>> {

    private final List<F> filters = new ArrayList<>();

    private final ConstantPool constants = new ConstantPool();

    // for example for put the foreign value in the next sub process
    @Deprecated
    private final Map<Object, Object> properties = new LinkedHashMap<>();


    @Deprecated
    public void property(@NotNull Object key, Object value) {
        properties.put(key.toString(), value);
    }

    @Deprecated
    public Object property(@NotNull Object key) {
        return properties.get(key.toString());
    }

    @SuppressWarnings("unchecked")
    public void appendFilters(List<Filter<?, ?>> filters) {
        this.filters.addAll((Collection<? extends F>) filters);
    }
}