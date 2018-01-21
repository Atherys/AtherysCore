package com.atherys.core.commands;

import com.atherys.core.damage.sources.AtherysDamageSources;
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

            if ( !player.isPresent() ) {
                src.sendMessage( Text.of( "Couldn't find target." ) );
                return CommandResult.empty();
            }

            Player entity = (Player) src;

            player.get().damage(
                    amount.orElse(0.0d),
                    AtherysDamageSources.of( entity,
                            AtherysDamageSources.arcaneDamage( entity ),
                            AtherysDamageSources.fireDamage( entity ),
                            AtherysDamageSources.ballisticDamage( entity ),
                            AtherysDamageSources.unarmedDamage( entity )
                    ).build()
            );
        }
        return CommandResult.empty();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .arguments(
                        GenericArguments.playerOrSource( Text.of("player") ),
                        GenericArguments.doubleNum( Text.of("amount") )
                )
                .executor( this )
                .build();
    }

}
