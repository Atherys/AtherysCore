package com.atherys.core;

import com.atherys.core.party.PartyManager;
import com.atherys.core.party.commands.PartyCommand;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import javax.inject.Inject;

import java.io.IOException;

import static com.atherys.core.AtherysCore.*;

@Plugin( id = ID, version = VERSION, name = NAME, description = DESCRIPTION)
public class AtherysCore {
    public static final String ID = "atherys-core";
    public static final String NAME = "A'therys Core";
    public static final String DESCRIPTION = "The core utilities used on the A'therys Horizons server.";
    public static final String VERSION = "1.0.0a";

    private static final AtherysCore instance = new AtherysCore();

    private static boolean init = false;

    @Inject
    private Logger logger;

    @Inject
    private Game game;

    private String directory = "config/" + ID;

    private static CoreConfig config;

    private void init() {
        try {
            config = new CoreConfig();
        } catch (IOException e) {
            e.printStackTrace();
            init = false;
            return;
        }

        if ( config.isDefault() || config.defaultConfig ) {
            config.save();
            logger.error( "AtherysCore config set to default. Plugin will halt. Please modify defaultConfig in config.conf to 'false' once non-default values have been inserted." );
            init = false;
            return;
        } else config.load();

        init = true;
    }

    private void start() {
        PartyManager.getInstance().loadAll();

        Sponge.getCommandManager().register( this, new PartyCommand().getCommandSpec(), "party" );
    }

    private void stop() {
        PartyManager.getInstance().saveAll();
    }

    @Listener
    public void onInit (GameInitializationEvent event) {
        init();
    }

    @Listener
    public void onStart (GameStartingServerEvent event) {
        if ( init ) start();
    }

    @Listener
    public void onStop (GameStoppingServerEvent event) {
        stop();
    }

    public static AtherysCore getInstance() {
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }

    public Game getGame() {
        return game;
    }

    public static CoreConfig getConfig() {
        return config;
    }

    public String getWorkingDirectory() {
        return directory;
    }
}
