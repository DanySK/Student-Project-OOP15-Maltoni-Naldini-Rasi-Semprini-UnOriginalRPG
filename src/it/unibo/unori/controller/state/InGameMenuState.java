package it.unibo.unori.controller.state;

import it.unibo.unori.view.layers.InGameMenuLayer;

/**
 * This GameState models the state of an in-game menu opened during exploration.
 */
public class InGameMenuState extends AbstractGameState {
    private final Object inGameMenuModel; // TODO

    /**
     * Default constructor. It places a new layer with the in-game pop-up menu on the {@link it.unibo.unori.view.View}
     * of the {@link it.unibo.unori.controller.state.GameState} below in the stack.
     */
    public InGameMenuState() {
        super(new InGameMenuLayer(null)); // TODO

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

}
