package com.atherys.core.commands;

import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypes;
import com.atherys.core.damage.sources.AtherysDirectEntityDamageSource;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class TempAtherysDamageCommand implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if ( src instanceof Player ) {
            Optional<Player> player = args.getOne( "player" );
            Optional<Double> amount = args.getOne( "amount" );
            Optional<AtherysDamageType> source = args.getOne( "source" );

            if ( !source.isPresent() ) {
                src.sendMessage( Text.of( "Couldn't find damage type." ) );
                return CommandResult.empty();
            }

            if ( !player.isPresent() ) {
                src.sendMessage( Text.of( "Couldn't find target." ) );
                return CommandResult.empty();
            }

            player.get().damage(
                    amount.orElse(0.0d),
                    new AtherysDirectEntityDamageSource.Builder().entity( (Player) src).type( source.orElse( AtherysDamageTypes.UNARMED ).getPrimitive() ).atherysType( source.orElse( AtherysDamageTypes.UNARMED ) ).build()
            );
        }
        return CommandResult.empty();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .arguments(
                        GenericArguments.playerOrSource( Text.of("player") ),
                        GenericArguments.doubleNum( Text.of("amount") ),
                        GenericArguments.catalogedElement( Text.of("source"), AtherysDamageType.class )
                )
                .executor( this )
                .build();
    }

}
