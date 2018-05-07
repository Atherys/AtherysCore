package com.atherys.core;

import static com.atherys.core.AtherysCore.DESCRIPTION;
import static com.atherys.core.AtherysCore.ID;
import static com.atherys.core.AtherysCore.NAME;
import static com.atherys.core.AtherysCore.VERSION;

import com.atherys.core.damage.AtherysDamageType;
import com.atherys.core.damage.AtherysDamageTypeRegistry;
import com.atherys.core.damage.AtherysDamageTypes;
import com.atherys.core.damage.listeners.DamageListeners;
import com.atherys.core.party.PartyManager;
import com.atherys.core.party.commands.PartyCommand;
import java.io.IOException;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin(id = ID, version = VERSION, name = NAME, description = DESCRIPTION)
public class AtherysCore {

  public static final String ID = "atheryscore";
  public static final String NAME = "A'therys Core";
  public static final String DESCRIPTION = "The core utilities used on the A'therys Horizons server.";
  public static final String VERSION = "1.2.2";

  private static AtherysCore instance;

  private static boolean init = false;

  @Inject
  private Logger logger;

  @Inject
  private Game game;

  private String configDirectory = "config/" + ID;

  private static CoreConfig config;

  private void init() {
    instance = this;

    // initialize the static constructor...
    getLogger().info(AtherysDamageTypes.ARCANE.getName());

    game.getRegistry()
        .registerModule(AtherysDamageType.class, AtherysDamageTypeRegistry.getInstance());

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
    PartyManager.getInstance().loadAll();

    Sponge.getCommandManager().register(this, new PartyCommand().getCommandSpec(), "party");

    if (config.DAMAGE.ENABLED) {
      Sponge.getEventManager().registerListeners(this, new DamageListeners());
    }

  }

  private void stop() {
    PartyManager.getInstance().saveAll();
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
    return configDirectory;
  }
}
