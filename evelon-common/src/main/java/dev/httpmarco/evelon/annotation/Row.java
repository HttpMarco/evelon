package dev.httpmarco.evelon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Row {

    /**
     * @return a custom field name
     */
    String name() default "";

    /**
     * @return if the field is not used in the repository
     */
    boolean ignore() default false;

}