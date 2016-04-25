package it.unibo.unori.controller.state;

import it.unibo.unori.view.View;

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
     * Draws graphics information each frame of the game. Called each frame for the currently active state.
     */
    void render();

    /**
     * Called when entering in the state.
     */
    void onEnter();

    /**
     * Called when exiting from the state.
     */
    void onExit();

    /**
     * The method returns the current view of the GameState. It is useful for layering the representations.
     * 
     * @return the current view of the GameState
     */
    View getView();
}
