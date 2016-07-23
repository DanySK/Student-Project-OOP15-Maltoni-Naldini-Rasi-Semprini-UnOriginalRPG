package it.unibo.unori.controller.state;

import javax.swing.JPanel;

/**
 * Abstarct class that models a game state in the state machine controller. It implements the interface and defines the
 * shared method getLayer().
 */
public abstract class AbstractGameState implements GameState {
    private final JPanel stateLayer;

    /**
     * Default constructor of GameState.
     * 
     * @param stateLayer
     *            the Layer of this state
     */
    public AbstractGameState(final JPanel stateLayer) {
        this.stateLayer = stateLayer;
    }

    @Override
    public abstract void update(final double elapsedTime);

    @Override
    public abstract void onEnter();

    @Override
    public abstract void onExit();

    @Override
    public JPanel getLayer() {
        return this.stateLayer;
    }
}
