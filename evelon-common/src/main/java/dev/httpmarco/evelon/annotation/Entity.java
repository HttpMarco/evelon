package dev.httpmarco.evelon.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Entity {

    /**
     * Manipulate a name of repository, default value is empty and link to simple class name
     * @return a new custom name
     */
    String name() default "";

}
