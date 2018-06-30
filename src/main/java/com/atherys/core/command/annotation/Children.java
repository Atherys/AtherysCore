package com.atherys.core.command.annotation;

import org.spongepowered.api.command.spec.CommandExecutor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Children {

    Class<? extends CommandExecutor>[] value();

}
