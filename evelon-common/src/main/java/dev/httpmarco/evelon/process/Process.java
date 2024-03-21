package dev.httpmarco.evelon.process;

import dev.httpmarco.evelon.query.Query;
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
public abstract class Process<T> {

    @Setter
    private String id;

    private final Repository<T> repository;
    private final Query<T> query;

    private final Set<RepositoryClass<?>> affectedRows = new LinkedHashSet<>();

    public Process(String id, Query<T> query, boolean mustPrepare) {
        this.id = id;
        this.query = query;
        this.repository = this.query.repository();

        if (mustPrepare) {
            this.prepare();
        }
    }

    public void prepare() {
        var type = this.repository.clazz().type();

        Check.stateCondition(type != Type.OBJECT, "The repository type is not an object type: " + type);

        for (var layer : repository.layers()) {
            ((SubStage) layer.stage(type)).attachAffectedRows(this, (RepositoryObjectClass<?>) repository.clazz());
        }
    }
}