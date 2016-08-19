package it.unibo.unori.controller.exceptions;

/**
 * This exception should be thrown when the two stacks of StateMachineStack are not synchronized anymore.
 */
public class DesynchronizedStackException extends IllegalStateException {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 5911768265201901749L;

    /**
     * Default constructor.
     */
    public DesynchronizedStackException() {
        super("Error in state machince stack: The graphic stack is not synchronized with logic stack.");
    }
}
