package it.unibo.unori.controller;

import it.unibo.unori.controller.state.GameState;

/**
 * This interface models a stack of {@link it.unibo.unori.controller.state.GameState}, to manage the current state easily,
 * keeping track of the state of the last GameState. It incorporates both model and graphic for each state.
 */
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
     * Looks at the GameState at the top of the stack without removing it from the stack.
     * 
     * @return the state peeked
     */
    GameState peek();
}
