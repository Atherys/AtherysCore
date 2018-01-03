package com.atherys.core.views;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Cornerstone of the Views API.
 * This class manages all {@link Viewable}-{@link View} class relationships.
 */
public final class ViewManager {

    private static ViewManager instance = new ViewManager();

    private Map<Class<? extends Viewable>, Class<? extends View>> views = new HashMap<>();

    private ViewManager() {}

    public static ViewManager getInstance() {
        return instance;
    }

    /**
     * Register a new {@link Viewable}-{@link View} relationship.
     * @param clazz The Viewable class.
     * @param view The View class.
     */
    public <T extends View<V>, V extends Viewable<T>> void registerView ( Class<V> clazz, Class<T> view ) {
        views.put( clazz, view );
    }

    /**
     * Removes an already-existing {@link Viewable}-{@link View} relationship.
     * @param clazz The Viewable class.
     */
    public <T extends View<V>, V extends Viewable<T>> void unregisterView ( Class<V> clazz ) {
        views.remove( clazz );
    }

    /**
     * Uses reflection to create an appropriate {@link View} for the given {@link Viewable} based upon a registered Viewable-View relationship.
     * @param object The viewable
     * @return The view. Null if such is not registered or a problem with instantiation occurred.
     */
    @SuppressWarnings("unchecked")
    @Nullable
    <T extends View<V>, V extends Viewable<T>> T createView ( V object ) {
        try {
            Class<T> view = (Class<T>) views.get( object.getClass() );
            if ( view == null ) return null;

            Constructor<T> constructor = view.getConstructor( object.getClass() );

            return constructor.newInstance( object );

        } catch (InstantiationException e ) {
            // TODO: Handle
            e.printStackTrace();
        } catch ( IllegalAccessException e ) {
            // TODO: Handle
            e.printStackTrace();
        } catch ( NoSuchMethodException e ) {
            // TODO: Handle
            e.printStackTrace();
        } catch ( InvocationTargetException e ) {
            // TODO: Handle
            e.printStackTrace();
        }
        return null;
    }
}
