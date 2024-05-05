package dev.httpmarco.evelon.query2;

import dev.httpmarco.evelon.layer.Layer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

@Getter
@Accessors
@AllArgsConstructor
public class Query<V> implements QueryMethod<V> {

    //private final Repository<?> asociatedRepository;
    private final Layer<?>[] usedLayers;

    public Query(@NotNull Set<Layer<?>> usedLayers) {
       //this.asociatedRepository = asociatedRepository;
        this.usedLayers = usedLayers.toArray(Layer[]::new);
    }

    @Override
    public void create(V value) {
        for (var layer : this.usedLayers) {
            layer.queryMethod().create(value);
        }
    }
}
