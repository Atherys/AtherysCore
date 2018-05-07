package com.atherys.core.party.commands;

import com.atherys.core.command.UserCommand;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.PartyMsg;
import java.util.Optional;
import javax.annotation.Nonnull;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;

public class PartyKickCommand extends UserCommand {

  @Nonnull
  @Override
  public CommandResult execute(@Nonnull User source, @Nonnull CommandContext args)
      throws CommandException {

    Optional<User> kickedUser = args.getOne("kickedPlayer");

    if (!kickedUser.isPresent()) {
      return CommandResult.empty();
    }

    if (!PartyManager.getInstance().hasPlayerParty(source)) {
      PartyMsg.error(source, "You are not in a party.");
      return CommandResult.success();
    }

    if (!PartyManager.getInstance().hasPlayerParty(kickedUser.get())) {
      PartyMsg.error(source, "That player is not in your party.");
      return CommandResult.success();
    }

    PartyManager.getInstance().getPlayerParty(source).ifPresent(party -> {

      if (!party.isLeader(source)) {
        PartyMsg.error(source, "You are not the leader of your party.");
        return;
      }

      PartyManager.getInstance().getPlayerParty(kickedUser.get()).ifPresent(kickedUserParty -> {
        if (!party.equals(kickedUserParty)) {
          PartyMsg.error(source, "That player is not in your party.");
        } else {
          party.removePlayer(kickedUser.get());
          PartyMsg.error(party, kickedUser.get().getName(), " has been kicked from the party.");
        }
      });
    });

    return CommandResult.success();
  }

  public CommandSpec getCommandSpec() {
    return CommandSpec.builder()
        .permission("atherys.core.party.kick")
        .executor(this)
        .arguments(
            GenericArguments.player(Text.of("kickedPlayer"))
        )
        .build();
  }

//    @Override
//    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
//        if ( src instanceof Player) {
//            Player player = (Player) src;
//
//            Optional<Player> kickedPlayerOpt = args.getOne("kickedPlayer");
//
//            player.get(CoreKeys.PARTY ).ifPresent( partyUUID -> PartyManager.getInstance().getParty( partyUUID ).ifPresent(party -> {
//
//                if ( party.getLeader().equals( player.getUniqueId() ) ) {
//
//                    kickedPlayerOpt.ifPresent( kickedPlayer -> kickedPlayer.get( CoreKeys.PARTY ).ifPresent(kickedPartyUUID -> {
//
//                        if ( kickedPartyUUID.equals( partyUUID ) ) {
//                            party.removePlayer( kickedPlayer );
//                            for ( User user : party.getMembers() ) {
//                                PartyMsg.error( user, kickedPlayer.getName(), " has been kicked from the party." );
//                            }
//                        }
//
//                    }));
//
//                }
//            }));
//
//            return CommandResult.success();
//        }
//
//        return CommandResult.empty();
//    }
}
