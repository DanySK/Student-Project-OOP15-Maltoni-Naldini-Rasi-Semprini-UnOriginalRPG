package it.unibo.unori.controller;

import java.util.Stack;

import it.unibo.unori.controller.state.GameState;
import it.unibo.unori.controller.state.MainMenuState;

public class StateMachineStack {
    private final Stack<GameState> gsStack = new Stack<>();

    public StateMachineStack() {
        this.gsStack.push(new MainMenuState());
    }

    /**
     * The method calls the update method of the GameState at the top of the
     * stack.
     * 
     * @param elapsedTime
     *            the time elapsed from start of the game state
     */
    public void update(final double elapsedTime) {
        // TODO check method
        gsStack.peek().update(elapsedTime);
    }

    /**
     * The method calls the render method of the GameState at the top of the
     * stack.
     */
    public void render() {
        // TODO check method
        gsStack.peek().render();
    }

    /**
     * Push a GameState at the top of the stack.
     * 
     * @param state the state to push
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
