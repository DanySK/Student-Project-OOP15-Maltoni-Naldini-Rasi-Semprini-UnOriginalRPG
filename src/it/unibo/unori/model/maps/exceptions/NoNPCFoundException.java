package it.unibo.unori.model.maps.exceptions;

/**
 *  Describe a Exception to throw if an expected NPC is not found.
 *
 */
public class NoNPCFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2001888144055465746L;
    private static final String MESSAGE = "Error, NPC not found";

    /**
     * Constructor.
     */
    public NoNPCFoundException() {
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
