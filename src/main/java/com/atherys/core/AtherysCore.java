package com.atherys.core;

import com.atherys.core.party.PartyManager;
import com.atherys.core.party.commands.PartyCommand;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

import static com.atherys.core.AtherysCore.*;

@Plugin( id = ID, name = NAME, description = DESCRIPTION)
public class AtherysCore {
    public static final String ID = "atherys-core";
    public static final String NAME = "A'therys Core";
    public static final String DESCRIPTION = "The core utilities used on the A'therys Horizons server.";

    private static final AtherysCore instance = new AtherysCore();

    private static boolean init = false;

    @Inject
    private Logger logger;

    @Inject
    private Game game;

    private String directory = "config/" + ID;

    private static CoreConfig config;

    private void init() {
        init = true;
    }

    private void start() {

        File workingDir = new File( directory + "/config.conf" );
        try {
            if ( workingDir.mkdirs() && workingDir.createNewFile() ) {
                config = new CoreConfig( HoconConfigurationLoader.builder().setPath( workingDir.toPath() ).build() );
                config.load();
            } else {
                logger.error( "Failed to create config directory. ");
                return;
            }
        } catch (IOException e) {
            logger.error( "Failed to create config directory. ");
            e.printStackTrace();
            return;
        }

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
}
