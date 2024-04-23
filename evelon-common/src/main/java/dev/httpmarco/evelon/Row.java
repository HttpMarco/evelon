package dev.httpmarco.evelon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Row annotation
 * This annotation is used to mark a field with options
 * Only a setting is required
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Row {

    /**
     * Overwrite the field name with the given value
     * So you can overwrite the field name with the column name
     * @return the name
     */
    String id() default "";

    /**
     * Ignore the field
     * @return true if the field should be ignored
     */
    boolean ignore() default false;

}
