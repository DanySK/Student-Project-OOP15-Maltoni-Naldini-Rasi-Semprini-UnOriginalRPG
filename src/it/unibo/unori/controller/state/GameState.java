package it.unibo.unori.controller.state;
/**
 * This interface models a game state (eg. local map, world map, main menu, battle) in the state machine controller
 */
public interface GameState {

    /**
     * Updates data for each tick of the game. 
     * Called each frame for the currently active state.
     * 
     * @param elapsedTime the time elapsed from start of the game state
     */
    public void update(final float elapsedTime);

    /**
     * Draws graphics information each frame of the game. 
     * Called each frame for the currently active state.
     */
    public void render();

    /**
     * Called when entering in the state.
     */
    public void onEnter();

    /**
     * Called when exiting from the state.
     */
    public void onExit();

}
