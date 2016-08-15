package it.unibo.unori.controller.exceptions;

public class UnsupportedSwingConstantException extends IllegalArgumentException {
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -5024741113475218465L;
    
    private static final String DEFAULT_MESSAGE = "This SwingConstant is not supported";

    /**
     * Default constructor.
     */
    public UnsupportedSwingConstantException() {
        super(DEFAULT_MESSAGE);
    }

}
