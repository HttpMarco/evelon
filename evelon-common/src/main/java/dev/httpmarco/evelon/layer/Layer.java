package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.filtering.LayerFilterHandler;
import dev.httpmarco.evelon.query.Query;
import dev.httpmarco.evelon.query.response.QueryResponse;
import dev.httpmarco.evelon.query.response.UpdateResponse;
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
    private final LayerFilterHandler<?, ?> filterHandler;

    public Layer(LayerFilterHandler<?, ?> filterHandler) {
        // todo: remove
        stages.put(Type.PARAMETER, new ParameterStage());
        stages.put(Type.OBJECT, new ObjectSubStage());
        this.filterHandler = filterHandler;
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
    public abstract <T> void initialize(Query<T> repository);

    public abstract <T> UpdateResponse create(Query<T> query, Object value);

    public abstract <T> UpdateResponse deleteAll(Query<T> query);

    public abstract <T> QueryResponse<List<T>> findAll(Query<T> query);

    public abstract <T> QueryResponse<Boolean> exists(Query<T> query);

}