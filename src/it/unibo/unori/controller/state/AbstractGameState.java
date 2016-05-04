package it.unibo.unori.controller.state;

import it.unibo.unori.view.layers.Layer;

public abstract class AbstractGameState implements GameState{
    private final Layer stateLayer;

    /**
     * Default constructor of GameState.
     * @param stateLayer the Layer of this state
     */
    public AbstractGameState(final Layer stateLayer) {
        this.stateLayer = stateLayer;
    }

    @Override
    public abstract void update(final double elapsedTime);

    @Override
    public abstract void onEnter();

    @Override
    public abstract void onExit();

    @Override
    public Layer getLayer() {
        return this.stateLayer;
    }
}
