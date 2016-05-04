package it.unibo.unori.controller.state;

import it.unibo.unori.view.View;
import it.unibo.unori.view.layers.Layer;

/**
 * This interface models a game state (eg. local map, world map, main menu, battle) in the state machine controller
 */
public interface GameState {

    /**
     * Updates data for each tick of the game. Called each frame for the currently active state.
     * 
     * @param elapsedTime
     *            the time elapsed from start of the game state
     */
    void update(final double elapsedTime);

    /**
     * Called when entering in the state.
     */
    void onEnter();

    /**
     * Called when exiting from the state.
     */
    void onExit();

    /**
     * The method returns the current Layer of the GameState, that paints the state.
     * 
     * @return the current view of the GameState
     */
    Layer getLayer();
}
