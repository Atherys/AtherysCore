package com.atherys.core.views;

import java.util.Optional;

/**
 * A Viewable is a type of object which can be read by an appropriate {@link View}.
 * @param <T> The View responsible for formatting this class.
 */
public interface Viewable<T extends View> {

    /**
     * Used to get an instance of an appropriate {@link View}.
     * @return The appropriate View for this Viewable. If no View has been registered with the ViewManager, or if the View could not be instantiated, this will be empty.
     */
    Optional<T> createView ();

}
