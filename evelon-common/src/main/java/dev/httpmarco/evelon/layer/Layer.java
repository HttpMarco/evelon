package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.query.response.UpdateResponse;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.stage.Stage;
import dev.httpmarco.evelon.stage.Type;
import dev.httpmarco.evelon.stage.common.ObjectSubStage;
import dev.httpmarco.evelon.stage.common.ParameterStage;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Accessors(fluent = true)
public abstract class Layer {

    // every layer has different stages, because different serialize methods
    private final Map<Type, Stage> stages = new HashMap<>();

    public Layer() {
        // todo: remove
        stages.put(Type.PARAMETER, new ParameterStage());
        stages.put(Type.OBJECT, new ObjectSubStage());
    }

    /**
     * Find the specific layer stage
     *
     * @param type of class or field
     * @return the current stage
     */
    public Stage stage(Type type) {
        return stages.get(type);
    }

    // default initialize method
    public abstract void initialize(Repository<?> repository);

    public abstract UpdateResponse create(Repository<?> repository, Object value);

    public abstract UpdateResponse deleteAll(Repository<?> repository);

    public abstract <T> QueryResponse<List<T>> findAll(Repository<T> repository);

    public abstract QueryResponse<Boolean> exists(Repository<?> repository);

}