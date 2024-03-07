package dev.httpmarco.evelon.common.repository.clazz;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
@SuppressWarnings("unchecked")
public class RepositoryClassImpl<T> implements RepositoryClass<T> {

    private final Class<T> clazz;
    private final Map<Model<?>, Stage<?, ?>> stages = new HashMap<>();

    public void newModel(Model<?> model) {
        stages.put(model, model.findStage(clazz));
    }

    @Override
    public <B extends Builder<B, ?>> Stage<T, B> stageOf(Model<B> model) {
        return (Stage<T, B>) stages.get(model);
    }
}
