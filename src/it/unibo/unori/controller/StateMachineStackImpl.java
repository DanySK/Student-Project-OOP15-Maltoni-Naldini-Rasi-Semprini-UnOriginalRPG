package it.unibo.unori.controller;

import java.util.Stack;

import it.unibo.unori.controller.state.GameState;
import it.unibo.unori.view.View;
import it.unibo.unori.view.layers.DialogLayer;
import it.unibo.unori.view.layers.InGameMenuLayer;
import it.unibo.unori.view.layers.Layer;

/**
 * This class models a stack of
 * {@link it.unibo.unori.controller.state.GameState}, to manage the current
 * state easily, keeping track of the state of the last GameState. It uses a
 * Stack<GameState> for model-side stack and a {@link it.unibo.view.View} for
 * view-side stack.
 */
public class StateMachineStackImpl implements StateMachineStack {
    private final Stack<GameState> gsStack;
    private final View layerStack;

    /**
     * Default constructor.
     */
    public StateMachineStackImpl() {
        this.gsStack = new Stack<>();
        this.layerStack = new View();
    }

    @Override
    public void pushAndRender(final GameState state) {
        this.push(state);
        this.render();
    }

    /**
     * {@inheritDoc} It is set final because firstly called by the constructor,
     * and it should not be overridden.
     * 
     */
    @Override
    public final void render() {
        final Layer currentLayer = this.gsStack.peek().getLayer();
        this.layerStack.push(currentLayer);
        if (!(currentLayer instanceof DialogLayer || currentLayer instanceof InGameMenuLayer)) {
            // DialogLayer should not resize the previous layer
            this.layerStack.resizeTo(currentLayer);
        }
        this.layerStack.run();
        this.layerStack.centerToScreen();
    }

    @Override
    public void push(final GameState state) {
        gsStack.push(state);
    }

    @Override
    public GameState pop() {
        this.layerStack.pop();
        return gsStack.pop();
    }

    @Override
    public GameState peek() {
        return this.gsStack.peek();
    }

    @Override
    public void closeTheView() {
        this.layerStack.close();
    }

    @Override
    public boolean isEmpty() {
        return this.gsStack.isEmpty();
    }
}
