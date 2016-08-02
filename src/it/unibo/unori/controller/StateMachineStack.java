package it.unibo.unori.controller;

import it.unibo.unori.controller.state.GameState;

public interface StateMachineStack {
    /**
     * The method calls the render method of the GameState at the top of the stack.
     */
    void render();

    /**
     * Push a GameState at the top of the stack.
     * 
     * @param state
     *            the state to push
     */
    void push(final GameState state);

    /**
     * Pop a GameState from the top of the stack.
     * 
     * @return the state popped
     */
    GameState pop();
    
    /**
     * Checks if there is MapState in the stack; if there is, it means the game should be started.
     * @return true if the game opened at least the map
     */
    boolean isGameReallyStarted();
}
