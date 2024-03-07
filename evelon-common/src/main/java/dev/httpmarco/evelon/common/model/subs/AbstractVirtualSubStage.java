package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;

import java.util.function.Consumer;

public abstract class AbstractVirtualSubStage<B extends Builder<B, ?>> implements SubStage<Object, B> {

    @Override
    public void initialize(String stageId, Model<B> model, RepositoryField<?> ownField, RepositoryObjectClass<?> clazz, B queries) {
        for (var field : clazz.fields()) {
            permitOnStage(field, model,
                    subStage -> subStage.initialize(
                            stageId + "_" + field.id(),
                            model,
                            field,
                            field.clazz().asObjectClass(),
                            queries.subBuilder(field.id())
                    ),
                    elementStage -> appendParameter(queries, field)
            );
        }
    }

    @Override
    public void create(Object value, String stageId, Model<B> model, RepositoryField<Object> ownField, RepositoryObjectClass<?> clazz, B queries) {
        for (var field : clazz.fields()) {
            permitOnStage(field, model,
                    subStage -> {
                        // todo
                    },
                    elementStage -> {
                        queries.appendValue((field.value(value)));
                        this.appendParameter(queries, field);
                    }
            );
        }
    }


    public abstract void appendParameter(B query, RepositoryField<?> field);

    @Override
    public boolean isElement(Class<?> type) {
        return true;
    }

    private <T> void permitOnStage(RepositoryField<T> field, Model<B> model, Consumer<SubStage<T, B>> subStageHandling, Consumer<ElementStage<T, ?, B>> elementStageHandling) {
        var stage = field.clazz().stageOf(model);
        if (stage.isSubStage()) {
            subStageHandling.accept(stage.asSubStage());
        } else if (stage.isElementStage()) {
            elementStageHandling.accept(stage.asElementStage());
        } else {
            throw new RuntimeException("Unknown stage type: " + stage.getClass().getSimpleName());
        }
    }
}
