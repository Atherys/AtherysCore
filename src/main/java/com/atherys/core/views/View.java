package com.atherys.core.views;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;

/**
 * A View is a type of object which will format and display an instance of a given class to a player.
 * Views must contains at least one public constructor, which accepts the appropriate type as an argument, and nothing else.
 * @param <T> The class which this view will be formatting
 */
public interface View<T extends Viewable> {

    /**
     * Used to send this view to the player. Depending on implementaiton, this can be done via {@link Player#sendMessage(Text)}, {@link Player#sendBookView(BookView)} or other.
     * @param player The player this view will be displayed to.
     */
    void show ( Player player );

}
