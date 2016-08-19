package it.unibo.unori.controller.exceptions;

/**
 * This exception should be thrown when a conversion between {@link javax.swing.SwingConstants} and
 * {@link it.unibo.unori.model.maps.Party.CardinalPoints}, but the object to be converted is not supported.
 */
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
