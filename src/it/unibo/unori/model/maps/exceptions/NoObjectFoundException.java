package it.unibo.unori.model.maps.exceptions;

/**
 * 
 * Exception to throw if an expected GameObject is not present.
 *
 */
public class NoObjectFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 8148330675328861604L;
    private static final String MESSAGE = "Error, GameObject not found";

    /**
     * Constructor.
     */
    public NoObjectFoundException() {
        super(MESSAGE);
    }

    /**
     * Describe the exception.
     * 
     * @return a description of the exception.
     */
    public String toString() {
        return MESSAGE;
    }

}
