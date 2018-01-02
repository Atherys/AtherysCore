package com.atherys.core.views;

import java.util.Optional;

/**
 * An abstract implementation of {@link Viewable}
 */
public abstract class AbstractViewable<T extends View<V>, V extends Viewable<T>> implements Viewable<T> {

    /**
     * Creates an instance of the appropriate {@link View} using {@link ViewManager#createView(Viewable)} )}.
     * @return An instance of the View. Returns empty if there was an issue with instantiation, or no View has been registered.
     */
    @SuppressWarnings( "unchecked" )
    @Override
    public Optional<T> createView() {
        return Optional.ofNullable( ViewManager.getInstance().createView( (V) this) );
    }
}
