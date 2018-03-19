package com.atherys.core.utils;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.action.TextActions;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.function.Consumer;

/**
 * A utility for polling players with arbitrary questions, and offering an arbitrary amount of answers with various actions attached.<br>
 * The questions may be sent to the player either in the form of a chat message ( {@link #pollChat(Player)} ),
 * a book view ( {@link #pollBook(Player)} ),
 * or a combination of the two in the form of a chat-based view button ( {@link #pollViewButton(Player, Text)} ).
 */
public final class Question {

    public static Text QUESTION_DECORATION_TOP = Text.of( TextColors.DARK_AQUA, "{", TextColors.AQUA, "Question", TextColors.DARK_AQUA, "}\n" );
    public static Text QUESTION_DECORATION_BOT = Text.of( TextColors.DARK_AQUA, "\n" );

    private static Map<UUID, Question> questions = new HashMap<>();

    public static class Builder {

        private Question question;

        private Builder ( Text question ) {
            this.question = new Question( question );
        }

        /**
         * @param answer A possible {@link Answer} to this question.
         * @return The builder for chaining.
         */
        public Builder addAnswer ( Answer answer ) {
            question.addAnswer( answer );
            return this;
        }

        /**
         * @return The {@link Question} object.
         */
        public Question build () {
            return question;
        }

    }

    public static class Answer {

        private Text text;
        private Consumer<Player> action;

        private Answer ( Text text ) {
            this.text = text;
        }

        private Answer ( Text text, Consumer<Player> action ) {
            this.text = text;
            this.action = action;
        }

        /**
         * Factory method for creating an answer.
         *
         * @param name   The display-text of this answer. This will be shown to the player as a clickable text object.
         * @param action The result of this answer. This will be executed in the event that the player clicks this answer.
         * @return The answer.
         */
        public static Answer of ( Text name, Consumer<Player> action ) {
            return new Answer( name, action );
        }

        /**
         * @return The display text of this answer.
         */
        public Text getText () {
            return text;
        }

        /**
         * @return The consumer which will be executed upon answering a question with this answer.
         */
        Consumer<Player> getAction () {
            return action;
        }

        public void setAction ( Consumer<Player> action ) {
            this.action = action;
        }

        /**
         * @param source The player who has answered the question
         */
        public void execute ( Player source ) {
            action.accept( source );
        }
    }


    private UUID id;
    private Text question;
    private List<Answer> answers;

    Question ( Text question ) {
        this.question = question;
        this.answers = new LinkedList<>();
        this.id = UUID.randomUUID();
    }

    public static Builder of ( Text question ) {
        return new Builder( question );
    }

    public UUID getId () {
        return id;
    }

    public Text getQuestion () {
        return question;
    }

    public List<Answer> getAnswers () {
        return answers;
    }

    void addAnswer ( Answer answer ) {
        this.answers.add( answer );
    }

    /**
     * Get the question as an interactable Text object. Above it will be placed the QUESTION_DECORATION_TOP, and after it the QUESTION_DECORATION_BOT.
     *
     * @return the Text object.
     */
    public Text asText () {
        Text.Builder builder = Text.builder();
        builder.append( QUESTION_DECORATION_TOP );
        builder.append( question );
        builder.append( Text.of( "\n" ) );

        for ( Answer answer : answers ) {
            builder.append( Text.of( TextStyles.RESET, TextColors.RESET, "[" ) );
            builder.append( Text.builder().append( answer.getText() )
                    .onHover( TextActions.showText( Text.of( "Click to answer" ) ) )
                    .onClick( TextActions.executeCallback( source -> {
                                if ( !( source instanceof Player ) ) {
                                    source.sendMessage( Text.of( TextColors.RED, "Must be a player to reply to a question." ) );
                                    return;
                                }

                                if ( questions.containsKey( this.id ) ) {
                                    answer.execute( (Player) source );
                                    questions.remove( this.id );
                                } else {
                                    source.sendMessage( Text.of( TextColors.RED, "You have already responded to that question!" ) );
                                }
                            }
                    ) )
                    .build() );
            builder.append( Text.of( TextStyles.RESET, TextColors.RESET, "] " ) );
        }

        builder.append( QUESTION_DECORATION_BOT );
        return builder.build();
    }

    /**
     * Poll a player with this question via chat message ( See: {@link #asText()} ).
     *
     * @param player The player to be polled.
     */
    public void pollChat ( @Nonnull Player player ) {
        questions.put( id, this );
        player.sendMessage( this.asText() );
    }

    /**
     * Poll a player with this question via book view.
     *
     * @param player The player to be polled.
     */
    public void pollBook ( @Nonnull Player player ) {
        questions.put( id, this );
        player.sendBookView( BookView.builder().addPage( asText() ).build() );
    }

    /**
     * Poll a player with an interactable Text object. Clicking it will result in a BookView appearing and the player having to respond to the question.
     *
     * @param player     The player to be polled.
     * @param buttonText The display text of the button.
     */
    public void pollViewButton ( @Nonnull Player player, @Nonnull Text buttonText ) {
        questions.put( id, this );
        Text text = Text.builder()
                .append( buttonText )
                .onHover( TextActions.showText( Text.of( TextColors.AQUA, "Click to View" ) ) )
                .onClick( TextActions.executeCallback( source -> {
                    if ( source instanceof Player ) this.pollBook( (Player) source );
                } ) )
                .build();

        player.sendMessage( text );
    }
}


