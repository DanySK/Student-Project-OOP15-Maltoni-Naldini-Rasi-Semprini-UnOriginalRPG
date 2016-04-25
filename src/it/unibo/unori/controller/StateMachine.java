package it.unibo.unori.controller;

import it.unibo.unori.controller.state.MainMenuState;

/**
 *
 */
public class StateMachine implements Controller {
    private final StateMachineStack stack;
    private final TimeCounter time;

    /**
     * This constructor instantiates a new StateMachine controller class and accept externally defined
     * {@link it.unibo.unori.Controller.StateMachineStack} and {@link it.unibo.unori.controller.TimeCounter}.
     * 
     * @param stack
     *            the externally defined StateMachineStack
     * @param time
     *            the externally defined TimeCounter
     */
    public StateMachine(final StateMachineStack stack, final TimeCounter time) {
        this.stack = stack;
        this.time = time;
    }

    /**
     * This default constructor instantiates a new StateMachine controller class, adding a new
     * {@link it.unibo.unori.Controller.StateMachineStack} with a new
     * {@link it.unibo.unori.controller.state.MainMenuState} pushed at the top.
     * It incorporates a TimeCounter, but needs to be started.
     */
    public StateMachine() {
        this(new StateMachineStack(), new TimeCounterImpl(false));
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
