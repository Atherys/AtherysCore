package com.atherys.core.database.mongo;

import com.atherys.core.database.api.Database;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.Arrays;

/**
 * An implementation of Database for the Morphia ORM library
 */
public class MorphiaDatabase implements Database<Datastore> {

    private Morphia morphia;
    private MongoClient client;
    private Datastore datastore;

    private MorphiaDatabase (MongoDatabaseConfig config) {
        this.morphia = new Morphia();
        this.client = new MongoClient(
                new ServerAddress(config.HOST, config.PORT),
                Arrays.asList(
                        MongoCredential
                                .createCredential(
                                        config.USERNAME,
                                        config.USER_DB,
                                        config.PASSWORD.toCharArray()
                                )
                )
        );
    }

    /**
     * This constructor creates a new Morphia instance with the given configuration and maps the provided classes
     * @param config The config
     * @param entityClasses The class entities this database will be mapping
     */
    protected MorphiaDatabase(MongoDatabaseConfig config, Class<?>... entityClasses) {
        this(config);
        morphia.map(entityClasses);
        datastore = morphia.createDatastore(client, config.NAME);
    }

    /**
     * This constructor creates a new Morphia instance with the given configuration and maps all relevat classes
     * from the provided package.
     * @param config The config
     * @param mapPackage The package to map
     */
    protected MorphiaDatabase(MongoDatabaseConfig config, String mapPackage) {
        this(config);
        morphia.mapPackage(mapPackage, true);
        datastore = morphia.createDatastore(client, config.NAME);
    }

    @Override
    public Datastore getDatabase() {
        return datastore;
    }
}
