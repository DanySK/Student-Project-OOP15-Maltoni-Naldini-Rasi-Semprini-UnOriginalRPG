package it.unibo.unori.model.maps.exceptions;

/**
 * 
 * Describe a Exception to throw if a expected map is not found.
 *
 */
public class NoMapFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1817184702262076641L;
    private static final String MESSAGE = "Error, Map not found";

    /**
     * Constructor.
     */
    public NoMapFoundException() {
        super(MESSAGE);
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
