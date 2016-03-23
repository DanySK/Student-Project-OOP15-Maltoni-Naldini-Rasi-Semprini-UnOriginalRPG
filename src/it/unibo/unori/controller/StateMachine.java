package it.unibo.unori.controller;
/**
 *
 */
public class StateMachine implements Controller {
    private StateMachineStack stack = new StateMachineStack();
    private TimeCounter time;

    @Override
    public void begin() {
        // TODO Auto-generated method stub
        stack.update(0);
        stack.render();
    }

}
