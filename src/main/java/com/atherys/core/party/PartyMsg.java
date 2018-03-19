package com.atherys.core.party;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

/**
 * A utility class for formatting and sending {@link Party}-related messages to players.
 */
public class PartyMsg {

    /**
     * The prefix of all {@link Party}-related messages;
     */
    public static final Text MSG_PREFIX = Text.of( TextColors.GREEN, "[", TextStyles.BOLD, TextColors.DARK_AQUA, "Party", TextStyles.RESET, TextColors.GREEN, "]", TextColors.RESET );

    /**
     * Sends an info message to all members of the given party. Uses {@link Party#getMembers()}
     *
     * @param party The party to whose members the message will be sent.
     * @param msg   The message. Will later be wrapped in a {@link Text} object.
     */
    public static void info( Party party, Object... msg ) {
        for ( User user : party.getMembers() ) {
            PartyMsg.info( user, msg );
        }
    }

    /**
     * Sends an error message to all members of the given party. Uses {@link Party#getMembers()}
     *
     * @param party The party to whose members the message will be sent.
     * @param msg   The message. Will later be wrapped in a {@link Text} object.
     */
    public static void error( Party party, Object... msg ) {
        for ( User user : party.getMembers() ) {
            PartyMsg.error( user, msg );
        }
    }

    /**
     * Sends an info message to the given player.
     *
     * @param player The player to whom the message will be sent.
     * @param msg    The message. Will later be wrapped in a {@link Text} object.
     */
    public static void info( Player player, Object... msg ) {
        player.sendMessage( Text.of( MSG_PREFIX, TextColors.DARK_AQUA, " ", Text.of( msg ) ) );
    }

    /**
     * Sends an error message to the given player.
     *
     * @param player The player to whom the message will be sent.
     * @param msg    The message. Will later be wrapped in a {@link Text} object.
     */
    public static void error( Player player, Object... msg ) {
        player.sendMessage( Text.of( MSG_PREFIX, TextColors.RED, " ", Text.of( msg ) ) );
    }

    /**
     * Sends an info message to the given user. Uses {@link User#isOnline()} to determine if the user is online first. If so, the message will be delivered, else not.
     *
     * @param user The user to whom the message will be sent.
     * @param msg  The message. Will later be wrapped in a {@link Text} object.
     */
    public static void info( User user, Object... msg ) {
        if ( user.isOnline() && user.getPlayer().isPresent() ) info( user.getPlayer().get(), msg );
    }

    /**
     * Sends an error message to the given user. Uses {@link User#isOnline()} to determine if the user is online first. If so, the message will be delivered, else not.
     *
     * @param user The user to whom the message will be sent.
     * @param msg  The message. Will later be wrapped in a {@link Text} object.
     */
    public static void error( User user, Object... msg ) {
        if ( user.isOnline() && user.getPlayer().isPresent() ) error( user.getPlayer().get(), msg );
    }
}
