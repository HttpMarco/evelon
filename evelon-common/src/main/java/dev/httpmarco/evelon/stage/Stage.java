package dev.httpmarco.evelon.stage;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a de-/serialization stage for a specific type
 */
public class Stage {

    public static abstract class Type {

        /**
         * Check if following class is of the type of this stage
         * @param clazz the clazz of variable
         * @return if the variable is of the type this stage
         */
        public abstract boolean isType(@NotNull Class<?> clazz);

    }
}
