package it.unibo.unori.controller.state;

import it.unibo.unori.view.View;
import it.unibo.unori.view.layers.Layer;
import it.unibo.unori.view.layers.menus.InGameMenuLayer;

/**
 * This GameState models the state of an in-game menu opened during exploration.
 */
public class InGameMenuState implements GameState {
    private final Layer inGameMenuLayer;
    private final Object inGameMenuModel; // TODO

    /**
     * Default constructor. It places a new layer with the in-game pop-up menu on the {@link it.unibo.unori.view.View}
     * of the {@link it.unibo.unori.controller.state.GameState} below in the stack.
     * 
     * @param mapStateView
     *            the view of the GameState below
     * 
     */
    public InGameMenuState(final View mapStateView) {
        // I'd rather do that with a defensive copy, but for now it's nearly impossible
        this.inGameMenuLayer = new InGameMenuLayer();

        /*
         * Potrebbe essere una buona opzione poter passare i bottoni alla view tramite una strategy esterna, magari
         * fornita dal model
         */
        this.inGameMenuModel = new Object(); // TODO
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double elapsedTime) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onEnter() {
        // TODO Auto-generated method stub

        // TODO Probably useless method
    }

    @Override
    public void onExit() {
        // TODO Auto-generated method stub

        // TODO Not sure, but probably useless method
    }

    @Override
    public Layer getLayer() {
        // TODO Auto-generated method stub
        return this.inGameMenuLayer; // TODO probably it is better to use a defensive copy
    }

}
