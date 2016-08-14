package it.unibo.unori.controller.state;

import it.unibo.unori.view.layers.Layer;

/**
 * Abstract class that models a game state in the state machine controller. It
 * implements the interface and defines the shared method getLayer().
 */
public abstract class AbstractGameState implements GameState {
    private final Layer stateLayer;

    /**
     * Default constructor of GameState.
     * 
     * @param stateLayer
     *            the Layer of this state
     */
    public AbstractGameState(final Layer stateLayer) {
        this.stateLayer = stateLayer;
    }

    @Override
    public Layer getLayer() {
        return this.stateLayer;
    }
}
