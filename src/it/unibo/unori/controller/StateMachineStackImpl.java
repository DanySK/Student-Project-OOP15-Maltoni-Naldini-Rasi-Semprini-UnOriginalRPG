package it.unibo.unori.controller;

import java.util.Stack;

import javax.swing.JPanel;

import it.unibo.unori.controller.state.GameState;
import it.unibo.unori.controller.state.MapState;
import it.unibo.unori.view.View;

/**
 * This class models a stack of {@link it.unibo.unori.controller.state.GameState}, to manage the current state easily,
 * keeping track of the state of the last GameState. It uses a Stack<GameState> for model-side stack and a
 * {@link it.unibo.view.View} for view-side stack.
 */
public class StateMachineStackImpl implements StateMachineStack {
    private final Stack<GameState> gsStack = new Stack<>();
    private final View layerStack = new View();

    /**
     * {@inheritDoc} It is set final because firstly called by the constructor, and it should not be overridden.
     * 
     */
    @Override
    public final void render() {
        final JPanel currentLayer = this.gsStack.peek().getLayer();
        this.layerStack.push(currentLayer);
        this.layerStack.resizeTo(currentLayer);
        this.layerStack.center();
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

    @Override
    public boolean isGameReallyStarted() {
        return this.gsStack.stream().anyMatch(state -> MapState.class.isAssignableFrom(state.getClass()));
    }
}
