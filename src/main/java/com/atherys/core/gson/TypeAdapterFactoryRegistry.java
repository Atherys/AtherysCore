package com.atherys.core.gson;

import com.atherys.core.utils.RuntimeTypeAdapterFactory;
import com.google.gson.GsonBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A class for managing many RuntimeTypeAdapterFactories ( RTAF for short )
 */
public class TypeAdapterFactoryRegistry {

    private Map<Class<?>, RuntimeTypeAdapterFactory<?>> typeAdapterFactoryMap = new HashMap<>();

    /**
     * Adds a new class to RTAF pair to this Registry
     *
     * @param interfaceClass The interface class
     * @param rtaf           The RuntimeTypeAdapterFactory for the interface
     * @param <T>            The type
     */
    public <T> void add(Class<T> interfaceClass, RuntimeTypeAdapterFactory<T> rtaf) {
        this.typeAdapterFactoryMap.put(interfaceClass, rtaf);
    }

    /**
     * Looks through this Registry for an appropriate class-RTAF pair, and registers a Subtype to the corresponding RTAF
     *
     * @param interfaceClass Teh interface class
     * @param implementation The implementation class
     * @param <T>            The type
     */
    public <T> void registerSubtype(Class<T> interfaceClass, Class<? extends T> implementation) {
        get(interfaceClass).ifPresent(typeAdapterFactory -> typeAdapterFactory.registerSubtype(implementation));
    }

    /**
     * Looks through this Registry for an appropriate class-RTAF pair, and registers multiple subtypes
     * to the corresponding RTAF
     *
     * @param interfaceClass  Teh interface class
     * @param implementations The implementation classes
     * @param <T>             The type
     */
    public final <T> void registerSubtypes(Class<T> interfaceClass, Collection<Class<? extends T>> implementations) {
        get(interfaceClass).ifPresent(typeAdapterFactory -> {
            for (Class<? extends T> implementation : implementations)
                typeAdapterFactory.registerSubtype(implementation);
        });
    }

    /**
     * Looks through this Registry for an appropriate class-RTAF pair, and registers a Subtype to the corresponding RTAF
     * with an identifier
     *
     * @param interfaceClass Teh interface class
     * @param implementation The implementation class
     * @param <T>            The type
     */
    public <T> void registerSubtype(Class<T> interfaceClass, Class<? extends T> implementation, String identifier) {
        get(interfaceClass).ifPresent(typeAdapterFactory -> typeAdapterFactory.registerSubtype(implementation, identifier));
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<RuntimeTypeAdapterFactory<T>> get(Class<T> interfaceClass) {
        return Optional.ofNullable((RuntimeTypeAdapterFactory<T>) typeAdapterFactoryMap.get(interfaceClass));
    }

    /**
     * Registers all RTAFs contained within this Registry to the given GsonBuilder
     *
     * @param builder The builder
     */
    public void registerAll(GsonBuilder builder) {
        typeAdapterFactoryMap.values().forEach(builder::registerTypeAdapterFactory);
    }

}
