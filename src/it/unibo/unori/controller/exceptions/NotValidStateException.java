package it.unibo.unori.controller.exceptions;

/**
 * This exception should be thrown when a GameState instance was expected from
 * the stack, but another was given instead.
 */
public class NotValidStateException extends Exception {
    private static final String DEFAULT_FIRST_PART = "Lo stato di gioco ";
    private static final String DEFAULT_LAST_PART = " non corrisponde con nessuno degli stati previsti";
    private static final String DEFAULT_MESSAGE = DEFAULT_FIRST_PART + "attuale" + DEFAULT_LAST_PART;
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 710686335512848422L;

    /**
     * Default constructor.
     */
    public NotValidStateException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Default constructor. It let the developer specify the instance of the
     * unexpected GameState.
     * 
     * @param expectedStateName
     *            the name of the instance of the unexpected GameState
     */
    public NotValidStateException(final String expectedStateName) {
        super(DEFAULT_FIRST_PART + expectedStateName + DEFAULT_LAST_PART);
    }
}
