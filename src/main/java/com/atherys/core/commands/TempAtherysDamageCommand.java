package com.atherys.core.commands;

import com.atherys.core.AtherysCore;
import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypes;
import com.atherys.core.damage.sources.AtherysDamageSources;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.entity.damage.DamageTypes;
import org.spongepowered.api.text.Text;

import java.util.Optional;

public class TempAtherysDamageCommand implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if ( src instanceof Player ) {
            Optional<Player> player = args.getOne( "player" );
            Optional<AtherysDamageType> damageType = args.getOne( "type" );
            Optional<Double> amount = args.getOne( "amount" );

            if ( !damageType.isPresent() ) {
                src.sendMessage( Text.of( "Couldn't find damage type." ) );
            }

            if ( !player.isPresent() ) {
                src.sendMessage( Text.of( "Couldn't find target." ) );
                return CommandResult.empty();
            }

            AtherysCore.getInstance().getLogger().info( "Will do " + amount.orElse(0.0) + " of " + damageType.get().getName() + " Damage to " + player.get().getName() );

            player.get().damage(
                    amount.orElse(0.0d),
                    AtherysDamageSources.multipleDirectBuilder( (Player) src )
                            .type( DamageTypes.CUSTOM )
                            .addType(
                                    AtherysDamageSources.singleDirectMagic(
                                            (Player) src,
                                            damageType.orElse( AtherysDamageTypes.FIRE )
                                    ),
                                    0.5f
                            )
                            .addType(
                                    AtherysDamageSources.singleDirectMagic(
                                            (Player) src,
                                            AtherysDamageTypes.FIRE
                                    ),
                                    0.5f
                            )
                            .magical()
                            .build()
            );
        }
        return CommandResult.empty();
    }

    public CommandSpec getSpec() {
        return CommandSpec.builder()
                .arguments(
                        GenericArguments.playerOrSource( Text.of("player") ),
                        GenericArguments.doubleNum( Text.of("amount") ),
                        GenericArguments.catalogedElement( Text.of("type"), AtherysDamageType.class )
                )
                .executor( this )
                .build();
    }

}
