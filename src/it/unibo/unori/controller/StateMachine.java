package it.unibo.unori.controller;

import it.unibo.unori.controller.state.MainMenuState;

/**
 *
 */
public class StateMachine implements Controller {
    private final StateMachineStack stack;
    private final TimeCounter time;

    /**
     * This default constructor creates a new {@link it.unibo.unori.Controller.StateMachineStack} and pushes a new
     * {@link it.unibo.unori.controller.state.MainMenuState} at the top of the stack.
     */
    public StateMachine() {
        stack = new StateMachineStack();
        time = new TimeCounterImpl(false);
    }

    /**
     * {@inheritDoc} This is done by pushing a new MainMenuState and updating and rendering it.
     */
    @Override
    public void begin() {
        // TODO
        stack.push(new MainMenuState());
        stack.update(0);
        stack.render();
    }

    @Override
    public void startTimer() {
        this.startTimer(0);
    }

    @Override
    public void startTimer(final double alreadyPlayedTime) {
        time.setAlreadyPlayedTime(alreadyPlayedTime);
        time.startTimer();
    }

}
