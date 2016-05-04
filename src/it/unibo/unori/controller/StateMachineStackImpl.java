package it.unibo.unori.controller;

import java.util.Stack;

import it.unibo.unori.controller.state.GameState;

/**
 * This class models a stack of {@link it.unibo.unori.controller.state.GameState}, to manage the current state easily,
 * keeping track of the state of the last GameState.
 */
public class StateMachineStackImpl implements StateMachineStack {
    private final Stack<GameState> gsStack = new Stack<>();

    @Override
    public final void update(final double elapsedTime) {
        // TODO check method
        gsStack.peek().update(elapsedTime);
    }

    @Override
    public final void render() {
        // TODO check method
    }

    @Override
    public void push(final GameState state) {
        // TODO check method
        gsStack.push(state);
    }

    @Override
    public GameState pop() {
        // TODO check method
        return gsStack.pop();
    }
}
