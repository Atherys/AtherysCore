package com.atherys.core;

import com.atherys.core.combat.CombatLog;
import com.atherys.core.command.CommandService;
import com.atherys.core.db.DatabaseContext;
import com.atherys.core.db.JPAConfig;
import com.atherys.core.event.AtherysHibernateConfigurationEvent;
import com.atherys.core.event.AtherysHibernateInitializedEvent;
import com.atherys.core.serialize.DurationTypeSerializer;
import com.atherys.core.template.TemplateEngine;
import com.atherys.core.utils.EntityUtils;
import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.serialize.TypeSerializers;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.dialect.Database;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.Order;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStoppedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.service.economy.EconomyService;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Stream;

import static com.atherys.core.AtherysCore.*;

@Plugin(id = ID, version = VERSION, name = NAME, description = DESCRIPTION)
public class AtherysCore {

    public static final String ID = "atheryscore";
    public static final String NAME = "A'therys Core";
    public static final String DESCRIPTION = "The core utilities used on the A'therys Horizons server.";
    public static final String VERSION = "%PROJECT_VERSION%";

    private static AtherysCore instance;

    private static boolean init = false;

    @Inject
    private Logger logger;

    @Inject
    PluginContainer container;

    private CoreConfig coreConfig;

    private TemplateEngine templateEngine;

    private EconomyService economyService;

    private CombatLog combatLog;

    private DatabaseContext databaseContext;

    private void init() {
        instance = this;
        this.templateEngine = new TemplateEngine();

        TypeSerializers.getDefaultSerializers().registerType(TypeToken.of(Duration.class), new DurationTypeSerializer());

        try {
            coreConfig = new CoreConfig();
            coreConfig.init();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (coreConfig.DB_ENABLED) {
            databaseContext = new DatabaseContext(coreConfig.JPA_CONFIG, logger);
        }

        this.economyService = Sponge.getServiceManager().provide(EconomyService.class).orElse(null);

        this.combatLog = new CombatLog();
        combatLog.init();

        Sponge.getEventManager().post(new AtherysHibernateInitializedEvent(databaseContext.getEntityManagerFactory()));
        init = true;
    }

    private void stopped() {
        if (coreConfig.DB_ENABLED) {
            databaseContext.close();
        }
    }

    @Listener(order = Order.FIRST)
    public void onInit(GameInitializationEvent event) {
        init();
    }

    @Listener
    public void onStopped(GameStoppedServerEvent event) {
        if (init) {
            stopped();
        }
    }

    @Listener
    public void onPlayerDamage(DamageEntityEvent event, @Root EntityDamageSource source, @Getter("getTargetEntity") Player victim) {
        Entity rootEntity = EntityUtils.getRootEntity(source);

        if (!(rootEntity instanceof Player)) {
            return;
        }

        combatLog.initiateCombat((Player) rootEntity, victim);
    }

    @Listener
    public void onPlayerDeath(DestructEntityEvent.Death event, @Root Player attacker, @Getter("getTargetEntity") Player victim) {
        combatLog.endCombat(attacker, victim);
    }

    public static CommandService getCommandService() {
        return CommandService.getInstance();
    }

    public Logger getLogger() {
        return logger;
    }

    public static CoreConfig getConfig() {
        return getInstance().coreConfig;
    }

    public static AtherysCore getInstance() {
        return instance;
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return getDatabaseContext().getEntityManagerFactory();
    }

    public static TemplateEngine getTemplateEngine() {
        return getInstance().templateEngine;
    }

    public static CombatLog getCombatLog() {
        return getInstance().combatLog;
    }

    public static DatabaseContext getDatabaseContext() {
        return getInstance().databaseContext;
    }

    public static Optional<EconomyService> getEconomyService() {
        return Optional.ofNullable(getInstance().economyService);
    }
}
