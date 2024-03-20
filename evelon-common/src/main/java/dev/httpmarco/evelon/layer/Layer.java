package dev.httpmarco.evelon.layer;

import dev.httpmarco.evelon.process.common.InitializeProcess;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.stage.Stage;
import dev.httpmarco.evelon.stage.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public abstract class Layer {

    // every layer has different stages, because different serialize methods
    private final Map<Type, Stage> stages = new HashMap<>();

    /**
     * Find the specific layer stage
     * @param type of class or field
     * @return the current stage
     */
    public Stage stage(Type type) {
        return stages.get(type);
    }

    // default initialize method
    void initialize(Repository<?> repository){
        new InitializeProcess(repository.name(), repository).run();
    }
}