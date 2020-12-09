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

        FluentConfiguration cfg = new FluentConfiguration();

        cfg.dataSource(
                config.HIBERNATE.get(JPAConfig.URL_KEY),
                config.HIBERNATE.get(JPAConfig.USERNAME_KEY),
                config.HIBERNATE.get(JPAConfig.PASSWORD_KEY)
        );

        AtherysDatabaseMigrationEvent event = new AtherysDatabaseMigrationEvent();
        Sponge.getEventManager().post(event);

        cfg.locations(
                (String[]) event.getLocations().toArray()
        );

        new Flyway(cfg).migrate();

        logger.info("Database migration complete.");
    }

//    private static final String MIGRATIONS_TABLE_IDENTIFIER = "AtherysMigrations";
//
//    private static final String MIGRATION_TABLE_DEFINITION_SCRIPT = "sql/2020-04-28-AtherysCore-Create-Migrations-Table.sql";
//
//    private JPAConfig config;
//
//    private Logger logger;
//
//    public DatabaseMigrator(JPAConfig config, Logger logger) {
//        this.config = config;
//        this.logger = logger;
//    }
//
//    // Migration Naming Scheme:
//    // YYYY-MM-DD-PLUGIN_ID-DESCRIPTION.sql
//    public void migrate() {
//        logger.info("Starting database migration...");
//
//        AtherysDatabaseMigrationEvent event = new AtherysDatabaseMigrationEvent();
//        Sponge.getEventManager().post(event);
//
//        List<Migration> migrations = fetchMigrations(event.getScripts());
//
//        PGSimpleDataSource dataSource = new PGSimpleDataSource();
//        dataSource.setUrl(config.HIBERNATE.get(JPAConfig.URL_KEY));
//        dataSource.setUser(config.HIBERNATE.get(JPAConfig.USERNAME_KEY));
//        dataSource.setPassword(config.HIBERNATE.get(JPAConfig.PASSWORD_KEY));
//
//        try (Connection connection = dataSource.getConnection()) {
//            createMigrationsTable(connection);
//            for (Migration migration : migrations) {
//                applyMigration(connection, migration);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        logger.info("Database migration complete.");
//    }
//
//    private void createMigrationsTable(Connection connection) throws SQLException, IOException {
//        URL resource = this.getClass().getClassLoader().getResource(MIGRATION_TABLE_DEFINITION_SCRIPT);
//
//        if (resource == null) {
//            throw new IOException("Could not find migration table definition script.");
//        }
//
//        String migrationsTableCreationScript = resource.toString();
//
//        try (Statement statement = connection.createStatement()) {
//            statement.execute(migrationsTableCreationScript);
//        }
//    }
//
//    private void applyMigration(Connection connection, Migration migration) throws SQLException {
//        // Firstly, check if this script has been executed before, by name
//        try (PreparedStatement statement = connection.prepareStatement("SELECT 1 FROM ? WHERE name = ?")) {
//            statement.setString(1, MIGRATIONS_TABLE_IDENTIFIER);
//            statement.setString(2, migration.getName());
//
//            ResultSet resultSet = statement.executeQuery();
//
//            // If the result set is not empty, this script name already exists in the migrations table and should be skipped.
//            if (resultSet.next()) {
//                logger.info("Skipping {}...", migration.getName());
//                return;
//            }
//        }
//
//        logger.info("Executing {}...", migration.getName());
//
//        // Execute the migration
//        try (Statement statement = connection.createStatement()) {
//            statement.execute(migration.getContents());
//        }
//
//        // Insert the migration info in the table for future use ( like checking if it's been executed before )
//        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO ? (name, content, timeExecuted) VALUES (?, ?, ?)")) {
//            statement.setString(1, MIGRATIONS_TABLE_IDENTIFIER);
//            statement.setString(2, migration.getName());
//            statement.setString(3, migration.getContents());
//            statement.setTimestamp(4, Timestamp.valueOf(migration.getTimeExecuted()));
//
//            statement.execute();
//        }
//    }
//
//    private List<Migration> fetchMigrations(List<Path> files) {
//        LocalDateTime now = LocalDateTime.now();
//
//        return files.stream()
//                .filter(this::isValidMigrationScript)
//                .sorted()
//                .map(file -> {
//                    try {
//                        String name = file.getFileName().toString();
//                        String contents = new String(Files.readAllBytes(file));
//                        return new Migration(
//                                now,
//                                name,
//                                contents
//                        );
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    return null;
//                })
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//    }
//
//    private boolean isValidMigrationScript(Path file) {
//        return Files.isRegularFile(file) && file.endsWith(".sql");
//    }

}
