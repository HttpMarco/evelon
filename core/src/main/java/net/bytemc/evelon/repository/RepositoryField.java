package net.bytemc.evelon.repository;

import net.bytemc.evelon.annotations.RowName;
import net.bytemc.evelon.layers.RepositoryLayer;
import net.bytemc.evelon.misc.EvelonReflections;
import net.bytemc.evelon.model.Stage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RepositoryField {

    private final String name;
    private final Field field;

    private final Map<RepositoryLayer, Stage> layerStorages = new HashMap<>();

    public RepositoryField(@NotNull Field field, Repository<?> repository) {
        if (field.isAnnotationPresent(RowName.class)) {
            this.name = field.getDeclaredAnnotation(RowName.class).name();
        } else {
            this.name = field.getName();
        }
        this.field = field;

        for (RepositoryLayer layer : repository.layers()) {
            layerStorages.put(layer, layer.model().findStage(this));
        }
    }

    public String getName() {
        return this.name;
    }

    @Contract(pure = true)
    public @NotNull Class<?> type() {
        return this.field.getType();
    }

    public Object getValue(Object parent) {
        return EvelonReflections.getFieldValue(this.field, parent);
    }

    public Stage getStage(RepositoryLayer layer) {
        return this.layerStorages.get(layer);
    }
}
