package dev.httpmarco.evelon.repository;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.layer.PreppedLayer;
import dev.httpmarco.evelon.layer.LayerService;
import dev.httpmarco.evelon.repository.external.RepositoryObjectEntry;
import dev.httpmarco.evelon.repository.exception.RepositoryTypeNotAllowedException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

public final class RepositoryBuilder<T> {

    private final Class<T> clazz;
    private final Set<Layer> layers = new LinkedHashSet<>();

    private String id;

    @Contract(pure = true)
    RepositoryBuilder(@NotNull Class<T> clazz) {
        this.clazz = clazz;
        this.id = clazz.getSimpleName();
    }

    public RepositoryBuilder<T> withId(String id) {
        this.id = id;
        return this;
    }

    public RepositoryBuilder<T> withLayer(Class<? extends Layer> layer) {
        this.layers.add(LayerService.layerOf(layer));
        return this;
    }

    @Contract(" -> new")
    public @NotNull Repository<T> build() {
        if (RepositoryEntryType.find(id, clazz) instanceof RepositoryObjectEntry objectEntry) {
            var repository = new Repository<T>(objectEntry, this.layers);
            // check all layers are ready to be used
            for (var layer : this.layers) {
                // some layers need to be prepped before the object is returned
                if (layer instanceof PreppedLayer preppedLayer) {
                    preppedLayer.prepped(repository);
                }
            }
            return repository;
        }
        throw new RepositoryTypeNotAllowedException();
    }
}
