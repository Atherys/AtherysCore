package com.atherys.core.menu;

import com.mcsimonflash.sponge.teslalibs.inventory.Element;
import com.mcsimonflash.sponge.teslalibs.inventory.Layout;
import com.mcsimonflash.sponge.teslalibs.inventory.View;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.plugin.PluginContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * A view intended to have player-specific elements.
 */
public class PlayerView {
    private View.Builder view;
    private Layout layout;
    private Map<Integer, Function<Player, Element>> updateableElements;

    public PlayerView(View.Builder view, Layout layout) {
        this.view = view;
        this.layout = layout;
        this.updateableElements = new HashMap<>();
    }

    public PlayerView(View.Builder view, Layout layout, Map<Integer, Function<Player, Element>> elements) {
        this(view, layout);
        this.updateableElements = elements;
    }

    public void addUpdateableElement(int index, Function<Player, Element> element) {
        updateableElements.put(index, element);
    }

    public void updateElement(View view, Player player, int element) {
        if (updateableElements.containsKey(element)) {
            view.setElement(element, updateableElements.get(element).apply(player));
        }
    }

    public void updateElements(View view, Player player, int...elements) {
        for (int i : elements) {
            updateElement(view, player, i);
        }
    }

    public void updateAll(View view, Player player) {
        updateableElements.forEach((index, element) -> {
            view.setElement(index, element.apply(player));
        });
    }

    public View getView(Player player, PluginContainer container) {
        View playerView = view.build(container).define(layout);
        updateAll(playerView, player);
        return playerView;
    }
}
