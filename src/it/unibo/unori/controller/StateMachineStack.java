package it.unibo.unori.controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import it.unibo.unori.controller.state.GameState;

public class StateMachineStack {
    Map<String, GameState> mStates = new HashMap<>();
    LinkedList<GameState> mStack = new LinkedList<>();

    public void update(float elapsedTime) {
        // TODO check method
        GameState top = mStack.peek();
        top.update(elapsedTime);
    }

    public void render() {
        // TODO check method
        GameState top = mStack.peek();
        top.render();
    }

    public void push(String name) {
        // TODO check method
        GameState state = mStates.get(name);
        mStack.push(state);
    }

    public GameState pop() {
        // TODO check method
        return mStack.pop();
    }
}
