package it.unibo.unori.controller;

import it.unibo.unori.controller.state.GameState;

/**
 * This interface models a stack of {@link it.unibo.unori.controller.state.GameState}, to manage the current state
 * easily, keeping track of the state of the last GameState. It incorporates both model and graphic for each state.
 */
public interface StateMachineStack {
    /**
     * Because usually both methods push and render are called at the same time, this method unifies the two.
     * 
     * @see #push(GameState)
     * @see #render()
     * 
     * @param state
     *            the state to push
     */
    void pushAndRender(GameState state);

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

    /**
     * It closes all the graphic part of the view (but not pushing out of the stack), usually ending to the closing of
     * the program.
     */
    void closeTheView();

    /*
     * @throws {@link it.unibo.unori.controller.exceptions.DesynchronizedStackException} if stack does not contain the
     * same states
     */
    /**
     * It checks if the stack is empty.
     * 
     * @return true if both graphic and logic stack are empty
     */
    boolean isEmpty();
}
