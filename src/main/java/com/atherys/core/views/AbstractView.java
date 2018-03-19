package com.atherys.core.views;

/**
 * An abstract implementation of {@link View} with the appropriate constructor.
 */
public abstract class AbstractView<T extends Viewable<V>, V extends View<T>> implements View<T> {

    protected T object;

    protected AbstractView( T object ) {
        this.object = object;
    }

}
