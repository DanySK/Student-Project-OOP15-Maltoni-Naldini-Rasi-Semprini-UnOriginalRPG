package it.unibo.unori.model.maps.exceptions;

/**
 * Exception to rise when the party try to walk on a cell whose status in "BLOCKED".
 *
 */
public class BlockedPathException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7282142092547170919L;
    private static final String MESSAGE = "Error, Path is Blocked";

    /**
     * Empty Constructor.
     */
    public BlockedPathException() {
        super(MESSAGE);
    }

    /**
     * Constructor to catch and save another exception.
     * @param e
     *          Exception to catch and save.
     */
    public BlockedPathException(final Exception e) {
        super(e);
    }

    /**
     * Send a message when the Exception is thrown.
     * 
     * @return the message to show
     */
    public String toString() {
        return MESSAGE;
    }

}
