package com.atherys.core.command;

import com.atherys.core.command.annotation.Aliases;
import com.atherys.core.command.annotation.Children;
import com.atherys.core.command.annotation.Description;
import com.atherys.core.command.annotation.Permission;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

public final class CommandService {

    private static CommandService instance = new CommandService();

    public static CommandService getInstance() {
        return instance;
    }

    public <T extends CommandExecutor> void register(T executor, Object plugin)
            throws AnnotatedCommandException {
        Command command = buildCommandSpec(executor);
        Sponge.getCommandManager().register(plugin, command.getSpec(), command.getAliases());
    }

    public <T extends CommandExecutor> Command buildCommandSpec(T command)
            throws AnnotatedCommandException {

        Class<? extends CommandExecutor> commandClass = command.getClass();

        String[] aliases = getAliases(commandClass);

        CommandSpec.Builder spec = CommandSpec.builder();

        // set description
        if (commandClass.isAnnotationPresent(Description.class)) {
            String description = commandClass.getAnnotation(Description.class).value();
            spec.description(Text.of(description));
        }

        // set permission
        if (commandClass.isAnnotationPresent(Permission.class)) {
            String permission = commandClass.getAnnotation(Permission.class).value();
            spec.permission(permission);
        }

        // set children
        if (commandClass.isAnnotationPresent(Children.class)) {
            // if a parent command is also parameterized, throw exception
            if (command instanceof ParameterizedCommand) {
                throw AnnotatedCommandException.parentParameterizedCommand(commandClass);
            }

            Class<? extends CommandExecutor>[] childCommandClasses = commandClass
                    .getAnnotation(Children.class).value();
            for (Class<? extends CommandExecutor> child : childCommandClasses) {
                Command childSpec;
                // instantiate the child command
                try {
                    childSpec = buildCommandSpec(child.newInstance());
                } catch (InstantiationException | IllegalAccessException e) {
                    throw AnnotatedCommandException.childInstantiation(child);
                }
                spec.child(childSpec.getSpec(), childSpec.getAliases());
            }
        }

        // if the command has parameters, cast it to a ParameterizedClass, get the arguments, and apply them to the spec
        if (command instanceof ParameterizedCommand) {
            ParameterizedCommand parameterizedCommand = (ParameterizedCommand) command;
            spec.arguments(parameterizedCommand.getArguments());
        }

        spec.executor(command);

        return new Command(spec.build(), aliases);
    }

    private String[] getAliases(Class<? extends CommandExecutor> commandClass)
            throws AnnotatedCommandException {
        // All command must have at least 1 non-empty alias
        if (!commandClass.isAnnotationPresent(Aliases.class)) {
            throw AnnotatedCommandException.noAliases(commandClass);
        }

        String[] aliases = commandClass.getAnnotation(Aliases.class).value();

        // check for empty aliases, they're not allowed
        for (String alias : aliases) {
            if (alias.isEmpty()) {
                throw AnnotatedCommandException.emptyAlias(commandClass);
            }
        }

        return aliases;
    }

    public static class Command {

        String[] aliases;
        CommandSpec spec;

        public Command(CommandSpec spec, String... aliases) {
            this.aliases = aliases;
            this.spec = spec;
        }

        public String[] getAliases() {
            return aliases;
        }

        public CommandSpec getSpec() {
            return spec;
        }
    }

    public static class AnnotatedCommandException extends Exception {

        private AnnotatedCommandException(String error) {
            super(error);
        }

        public static AnnotatedCommandException noAliases(Class<?> commandClass) {
            return new AnnotatedCommandException(
                    "The " + commandClass.getName() + " class is not annotated with Aliases.");
        }

        public static AnnotatedCommandException emptyAlias(Class<?> commandClass) {
            return new AnnotatedCommandException(
                    "The " + commandClass.getName() + " class is annotated with an empty alias.");
        }

        public static AnnotatedCommandException childInstantiation(Class<?> commandClass) {
            return new AnnotatedCommandException(
                    "Failed to instantiate the " + commandClass.getName()
                            + " class. Ensure this class has an accessible no-args constructor available.");
        }

        public static AnnotatedCommandException parentParameterizedCommand(Class<?> commandClass) {
            return new AnnotatedCommandException("The " + commandClass.getName()
                    + " command class is both a parent ( Annotated with @Children ) and a ParameterizedCommand. Parent command ought not to have parameters.");
        }
    }

}
