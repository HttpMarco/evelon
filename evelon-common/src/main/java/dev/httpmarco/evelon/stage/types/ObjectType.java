package dev.httpmarco.evelon.stage.types;

import dev.httpmarco.evelon.Evelon;
import dev.httpmarco.evelon.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * This class is a stage type that is used to represent a type that is an object.
 */
public final class ObjectType extends Stage.Type {

    @Override
    public boolean isType(@NotNull Class<?> clazz) {
        // we only accept fields that are of a type that is a stage type
        return Arrays.stream(clazz.getDeclaredFields()).allMatch(it -> Evelon.instance().stageService().typeOf(it.getType()) != null);
    }
}
