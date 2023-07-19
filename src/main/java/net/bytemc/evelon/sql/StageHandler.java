package net.bytemc.evelon.sql;

import lombok.Getter;
import net.bytemc.evelon.sql.stages.DefaultParameterStage;
import net.bytemc.evelon.sql.stages.EnumerationStage;
import net.bytemc.evelon.sql.substages.VirtualObjectStage;
import net.bytemc.evelon.sql.transformers.RecordTransformer;

import java.util.ArrayList;
import java.util.List;

public final class StageHandler {

    @Getter
    private final static StageHandler instance;
    private final List<Stage<?>> stages = new ArrayList<>();

    static {
        instance = new StageHandler();
    }

    public StageHandler() {
        // for primitives, default java arguments
        this.stages.add(new DefaultParameterStage());

        // only for enumeration types -> mariadb extra type
        this.stages.add(new EnumerationStage());

        // transform record to object -> deny duplicated code
        this.stages.add(new RecordTransformer());

        // for custom user objects
        this.stages.add(new VirtualObjectStage());
    }


    /**
     * @param type clazz of the object
     * @return the current stage of the class, if not found return null
     */
    public Stage<?> getElementStage(Class<?> type) {
        return this.stages.stream().filter(it -> it.isElement(type)).findFirst().orElse(null);
    }

    /**
     * @param clazz clazz of the stage
     * @param <T>  type of the stage
     * @return the stage of the class, if not found return null
     */
    public <T> Stage<T> getStage(Class<T> clazz) {
        return (Stage<T>) this.stages.stream().filter(it -> it.getClass().equals(clazz)).findFirst().orElse(null);
    }

}
