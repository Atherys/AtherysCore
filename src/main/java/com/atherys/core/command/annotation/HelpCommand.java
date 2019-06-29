package com.atherys.core.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HelpCommand {

    /**
     * The title for the help command.
     */
    String title();

    /**
     * For use with child commands.
     */
    String prefix() default "";

    /**
     * The child command alias to use instead. For instance, "help". If left blank, no child
     * command will be generated.
     */
    String command() default "";
}
