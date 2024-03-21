package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.layer.Layer;
import dev.httpmarco.evelon.repository.Repository;
import dev.httpmarco.evelon.repository.RepositoryClass;
import dev.httpmarco.evelon.repository.RepositoryObjectClass;
import dev.httpmarco.evelon.stage.SubStage;
import dev.httpmarco.evelon.stage.Type;
import dev.httpmarco.osgan.utils.validate.Check;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.LinkedHashSet;
import java.util.Set;


@Getter
@Accessors(fluent = true)
public abstract class Process {

    @Setter
    private String id;
    private final ProcessMeta meta;
    private final Set<RepositoryClass<?>> affectedRows = new LinkedHashSet<>();

    public Process(String id, Repository<?> repository) {
        this.id = id;
        this.meta = new ProcessMeta(repository);

        this.prepare();
    }

    public void prepare() {
        var type = meta().repository().clazz().type();

        Check.stateCondition(type != Type.OBJECT, "The repository type is not an object type: " + type);

        for (var layer : meta().repository().layers()) {
            ((SubStage) layer.stage(type)).attachAffectedRows(this, (RepositoryObjectClass<?>) meta().repository().clazz());
        }
    }
}