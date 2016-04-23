package it.unibo.unori.controller;

import java.util.Stack;

import it.unibo.unori.controller.state.GameState;

/**
 * This class models a stack of {@link it.unibo.unori.controller.state.GameState}, to manage the current state easily,
 * keeping track of the state of the last GameState.
 */
public class StateMachineStack {
    private final Stack<GameState> gsStack = new Stack<>();

    /**
     * The method calls the update method of the GameState at the top of the stack. It is set final because firstly
     * called by the constructor, and it should not be overridden.
     * 
     * @param elapsedTime
     *            the time elapsed from start of the game state
     */
    public final void update(final double elapsedTime) {
        // TODO check method
        gsStack.peek().update(elapsedTime);
    }

    /**
     * The method calls the render method of the GameState at the top of the stack. It is set final because firstly
     * called by the constructor, and it should not be overridden.
     */
    public final void render() {
        // TODO check method
        gsStack.peek().render();
    }

    /**
     * Push a GameState at the top of the stack.
     * 
     * @param state
     *            the state to push
     */
    public void push(final GameState state) {
        // TODO check method
        gsStack.push(state);
    }

    /**
     * Pop a GameState from the top of the stack.
     * 
     * @return the state popped
     */
    public GameState pop() {
        // TODO check method
        return gsStack.pop();
    }
}
