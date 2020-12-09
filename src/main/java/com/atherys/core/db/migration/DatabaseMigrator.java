package com.atherys.core.db.migration;

import com.atherys.core.db.JPAConfig;
import com.atherys.core.event.AtherysDatabaseMigrationEvent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassPathUtils;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.flywaydb.core.api.configuration.Configuration;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.flywaydb.core.internal.callback.SqlScriptCallbackFactory;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DatabaseMigrator {

    private JPAConfig config;

    private Logger logger;

    public DatabaseMigrator(JPAConfig config, Logger logger) {
        this.logger = logger;
        this.config = config;
    }

    public void migrate() {
        logger.info("Beginning database migration...");

        String vendor = config.HIBERNATE.get(JPAConfig.URL_KEY).split(":")[1];

        AtherysDatabaseMigrationEvent event = new AtherysDatabaseMigrationEvent();
        Sponge.getEventManager().post(event);

        event.getPluginIds().forEach(pluginId -> {
            String location = String.format("classpath:./db/migration/%s/%s", vendor, pluginId);
            logger.info("Migrating " + location);

            FluentConfiguration cfg = new FluentConfiguration()
                    .dataSource(
                            config.HIBERNATE.get(JPAConfig.URL_KEY),
                            config.HIBERNATE.get(JPAConfig.USERNAME_KEY),
                            config.HIBERNATE.get(JPAConfig.PASSWORD_KEY)
                    )
                    .table("flyway_schema_history_" + pluginId)
                    .locations(location);

            new Flyway(cfg).migrate();
        });

        logger.info("Database migration complete.");
    }

}
