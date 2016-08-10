package it.unibo.unori.controller.exceptions;

/**
 * This exception should be thrown when a GameState instance was expected from
 * the stack, but another was given instead.
 */
public class NotValidStateException extends Exception {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 710686335512848422L;

    /**
     * Default constructor.
     */
    public NotValidStateException() {
        super("The current GameState is not what was expected");
    }
}
