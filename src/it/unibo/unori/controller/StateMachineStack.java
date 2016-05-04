package it.unibo.unori.controller;

import it.unibo.unori.controller.state.GameState;

public interface StateMachineStack {

    /**
     * The method calls the update method of the GameState at the top of the stack. It is set final because firstly
     * called by the constructor, and it should not be overridden.
     * 
     * @param elapsedTime
     *            the time elapsed from start of the game state
     */
    void update(final double elapsedTime);

    /**
     * The method calls the render method of the GameState at the top of the stack. It is set final because firstly
     * called by the constructor, and it should not be overridden.
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
}
