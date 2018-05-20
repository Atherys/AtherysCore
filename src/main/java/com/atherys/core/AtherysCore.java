package com.atherys.core;

import com.atherys.core.command.CommandService;
import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypeRegistry;
import com.atherys.core.damage.AtherysDamageTypes;
import com.atherys.core.damage.listeners.DamageListeners;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.commands.PartyCommand;
import com.atherys.core.party.data.PartyData;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataRegistration;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import javax.inject.Inject;
import java.io.IOException;

import static com.atherys.core.AtherysCore.*;

@Plugin(id = ID, version = VERSION, name = NAME, description = DESCRIPTION)
public class AtherysCore {

    public static final String ID = "atheryscore";
    public static final String NAME = "A'therys Core";
    public static final String DESCRIPTION = "The core utilities used on the A'therys Horizons server.";
    public static final String VERSION = "1.2.3";

    @Inject
    PluginContainer container;

    private static AtherysCore instance;

    private static boolean init = false;
    private static CoreConfig config;

    @Inject
    private Logger logger;

    private String configDirectory = "config/" + ID;

    public static AtherysCore getInstance() {
        return instance;
    }

    public static CoreConfig getConfig() {
        return config;
    }

    private void init() {
        instance = this;

        // initialize the static constructor...
        getLogger().info(AtherysDamageTypes.ARCANE.getName());

        Sponge.getRegistry().registerModule(AtherysDamageType.class, AtherysDamageTypeRegistry.getInstance());

        try {
            config = new CoreConfig();
            config.init();
        } catch (IOException e) {
            e.printStackTrace();
            init = false;
            return;
        }

        if (config.DEFAULT) {
            logger.error(
                    "AtherysCore config set to default. Plugin will halt. Please modify defaultConfig in config.conf to 'false' once non-default values have been inserted.");
            init = false;
            return;
        }

        init = true;

    }

    private void start() {
        if (config.DAMAGE.ENABLED) {
            Sponge.getEventManager().registerListeners(this, new DamageListeners());
        }

        if ( config.PARTIES_ENABLED ) {
            PartyManager.getInstance().loadAll();

            try {
                getCommandService().register(new PartyCommand(), this);
            } catch (CommandService.AnnotatedCommandException e) {
                e.printStackTrace();
            }
        }

    }

    private void stop() {
        if ( config.PARTIES_ENABLED ) PartyManager.getInstance().saveAll();
    }

    @Listener
    public void preInit(GamePreInitializationEvent event) {
        CoreKeys.PARTY_DATA_REGISTRATION = DataRegistration.builder()
                .dataClass(PartyData.class)
                .immutableClass(PartyData.Immutable.class)
                .builder(new PartyData.Builder())
                .dataName("Party")
                .manipulatorId("party")
                .buildAndRegister(this.container);
    }

    @Listener(order = Order.EARLY)
    public void onInit(GameInitializationEvent event) {
        init();
    }

    @Listener
    public void onStart(GameStartingServerEvent event) {
        if (init) {
            start();
        }
    }

    @Listener
    public void onStop(GameStoppingServerEvent event) {
        if (init) {
            stop();
        }
    }

    public static CommandService getCommandService() {
        return CommandService.getInstance();
    }

    public Logger getLogger() {
        return logger;
    }

    public String getWorkingDirectory() {
        return configDirectory;
    }
}
