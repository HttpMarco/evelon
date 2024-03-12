package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.Evelon;
import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.layers.EvelonModelLayer;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
public class RepositoryClassImpl<T> implements RepositoryClass<T> {

    private final Class<T> clazz;
    private final Map<Model<?>, Stage<T, ?>> stages = new HashMap<>();

    @Override
    public <B extends Builder<B, ?, ?>> Stage<T, B> stageOf(Model<B> model) {
        return (Stage<T, B>) stages.get(model);
    }

    public RepositoryClassImpl(Class<T> clazz) {
        this.clazz = clazz;

        // todo improvement
        for (var value : Evelon.instance().layerPool().cachedLayers().values()) {
            if (value instanceof EvelonModelLayer<?> modelLayer) {
                stages.put(modelLayer.model(), modelLayer.model().findStage(this.clazz));
            }
        }
    }
}
