package com.atherys.core.menu;

import com.mcsimonflash.sponge.teslalibs.inventory.Action;
import com.mcsimonflash.sponge.teslalibs.inventory.Element;
import com.mcsimonflash.sponge.teslalibs.inventory.Layout;
import com.mcsimonflash.sponge.teslalibs.inventory.View;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * A view intended to have player-specific elements.
 */
public class PlayerView {
    private View.Builder view;
    private Layout layout;
    private Consumer<Action<InteractInventoryEvent.Close>> onClose;

    private Map<Integer, Function<Player, Element>> updateableElements;
    private Map<UUID, View> players;

    public PlayerView(View.Builder view, Layout layout) {
        this.view = view;
        this.layout = layout;
        this.updateableElements = new HashMap<>();
        this.players = new HashMap<>();
    }

    public PlayerView(View.Builder view, Layout layout, Map<Integer, Function<Player, Element>> elements) {
        this(view, layout);
        this.updateableElements = elements;
    }

    public void setOnClose(Consumer<Action<InteractInventoryEvent.Close>> onClose) {
        this.onClose = onClose;
    }

    public void addUpdateableElement(int index, Function<Player, Element> element) {
        updateableElements.put(index, element);
    }

    public void updateElement(Player player, int element) {
        if (players.containsKey(player.getUniqueId()) && updateableElements.containsKey(element)) {
            players.get(player.getUniqueId()).setElement(element, updateableElements.get(element).apply(player));
        }
    }

    public void updateElements(Player player, int...elements) {
        if (players.containsKey(player.getUniqueId())) {
            View view = players.get(player.getUniqueId());
            for (int i : elements) {
                view.setElement(i, updateableElements.get(i).apply(player));
            }
        }
    }

    public void updateAll(Player player) {
        if (players.containsKey(player.getUniqueId())) {
            View view = players.get(player.getUniqueId());
            updateableElements.forEach((index, element) -> {
                view.setElement(index, element.apply(player));
            });
        }
    }

    public void switchToView(Player player, PlayerView playerView, PluginContainer container) {
        players.remove(player.getUniqueId());
        playerView.startView(player, container);
    }

    public void startView(Player player, PluginContainer container) {
        view.onClose(action -> {
            if (onClose != null) onClose.accept(action);
            players.remove(player.getUniqueId());
        });
        View playerView = view.build(container).define(layout);
        players.put(player.getUniqueId(), playerView);
        updateAll(player);
        playerView.open(player);
    }
}
