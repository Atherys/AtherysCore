package com.atherys.core.party;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

public class PartyMsg {

    public static final Text MSG_PREFIX = Text.of(TextColors.GREEN, "[", TextStyles.BOLD, TextColors.DARK_AQUA, "Party", TextStyles.RESET, TextColors.GREEN, "]", TextColors.DARK_AQUA );

    public static void info ( Party party, Object... msg ) {
        for ( User user : party.getMembers() ) {
            PartyMsg.info( user, msg );
        }
    }

    public static void error ( Party party, Object... msg ) {
        for ( User user : party.getMembers() ) {
            PartyMsg.error( user, msg );
        }
    }

    public static void info ( Player player, Text text ) {
        player.sendMessage( Text.of(MSG_PREFIX,  " ", text ) );
    }

    public static void error ( Player player, Text text ) {
        player.sendMessage( Text.of(MSG_PREFIX, TextColors.RED, " ", text ) );
    }

    public static void info (Player player, Object... msg ) {
        player.sendMessage( Text.of(MSG_PREFIX,  " ", Text.of ( msg ) ) );
    }

    public static void error (Player player, Object... msg ) {
        player.sendMessage( Text.of(MSG_PREFIX, TextColors.RED, " ", Text.of ( msg ) ) );
    }

    public static void info(User user, Object... msg ) {
        if ( user.isOnline() && user.getPlayer().isPresent() ) info ( user.getPlayer().get(), msg );
    }

    public static void error(User user, Object... msg ) {
        if ( user.isOnline() && user.getPlayer().isPresent() ) error ( user.getPlayer().get(), msg );
    }
}
