package dev.httpmarco.evelon.common.model.subs;

import dev.httpmarco.evelon.common.builder.Builder;
import dev.httpmarco.evelon.common.model.ElementStage;
import dev.httpmarco.evelon.common.model.Model;
import dev.httpmarco.evelon.common.model.Stage;
import dev.httpmarco.evelon.common.model.SubStage;
import dev.httpmarco.evelon.common.repository.Repository;
import dev.httpmarco.evelon.common.repository.RepositoryField;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryClass;
import dev.httpmarco.evelon.common.repository.clazz.RepositoryObjectClass;
import dev.httpmarco.osgan.reflections.Reflections;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Consumer;

public abstract class AbstractVirtualSubStage<B extends Builder<B, ?, ?>> implements SubStage<Object, B> {

    @Override
    public void initialize(Repository<?> repository, String stageId, Model<B> model, RepositoryField<?> ownField, @NotNull RepositoryObjectClass<?> clazz, B queries) {
        for (var field : clazz.fields()) {
            permitOnStage(field, model,
                    it -> {
                        it.initialize(repository,
                                (stageId + "_" + field.id()),
                                model,
                                field,
                                field.clazz().asObjectClass(),
                                queries.subBuilder(field.id(), field.parentClass()));
                    },
                    it -> appendParameter(queries, field)
            );
        }
    }

    @Override
    public void create(Object value, String stageId, Model<B> model, RepositoryField<?> ownField, @NotNull RepositoryObjectClass<?> clazz, B queries) {
        for (var field : clazz.fields()) {
            permitOnStage(field, model,
                    it -> {
                        var builder = queries.subBuilder(field.id(), field.parentClass());

                        it.createMapping(field.value(value),
                                (stageId + "_" + field.id()),
                                model,
                                field,
                                field.clazz().asObjectClass(),
                                builder);

                        // add values from primary field
                        for (var primaryField : field.parentClass().asObjectClass().primaryFields()) {
                            builder.appendValue(primaryField.id(), primaryField.value(value));
                        }
                    },
                    it -> {
                        queries.appendValue(field.id(), (field.value(value)));
                        this.appendParameter(queries, field);
                    }
            );
        }
    }

    @Override
    public Object construct(Model<B> model, RepositoryClass<?> clazz, B builder) {
        Reflections<?> reflections = Reflections.of(clazz.clazz());
        var object = reflections.allocate();
        reflections.withValue(object);

        // read all data from database
        for (var field : clazz.asObjectClass().fields()) {
            appendParameter(builder, field);
        }

        // convert data to objects
        transformData(builder, clazz.asObjectClass().fields());

        // overwrite all fields with data
        for (var field : clazz.asObjectClass().fields()) {
            permitOnStage(field, model,
                    it -> {
                    }, // todo
                    it -> {
                        // manipulate field with reflections
                        reflections.modify(field.field(), it.construct(model, field, builder));
                    }
            );
        }
        return object;
    }

    public abstract void appendParameter(B builder, RepositoryField<?> field);

    public abstract void transformData(B builder, RepositoryField<?>... requiredFields);

    @Override
    public boolean isElement(Model<B> model, Class<?> type) {
        return Arrays.stream(type.getDeclaredFields()).allMatch(it -> {
            var stage = model.findStage(it.getType());

            // check if sub elements are allowed to consume
            if (stage instanceof AbstractVirtualSubStage<?>) {
                return stage.isElement(model, it.getType());
            } else {
                return true;
            }
        });
    }

    /**
     * Order stage to right extended class
     *
     * @param field                field to be handled
     * @param model                model
     * @param subStageHandling     describe handling of sub stage
     * @param elementStageHandling describe handling of element stage
     * @param <T>                  type of field type
     */
    private <T> void permitOnStage(@NotNull RepositoryField<T> field, Model<B> model, Consumer<SubStage<T, B>> subStageHandling, Consumer<ElementStage<T, ?, B>> elementStageHandling) {
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
