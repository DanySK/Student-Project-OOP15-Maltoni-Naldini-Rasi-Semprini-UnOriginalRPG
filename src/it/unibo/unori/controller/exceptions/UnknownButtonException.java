package it.unibo.unori.controller.exceptions;

/**
 * This exception should be thrown when an ActionListener does not recognize the ActionCommand.
 */
public class UnknownButtonException extends Exception {
    private static final long serialVersionUID = 8999911786631477486L;
    private static final String DEFAULT_MESSAGE = "Unknown button pressed";

    /**
     * Default constructor.
     */
    public UnknownButtonException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructor that let the developer specify which action command was not recognized.
     * 
     * @param command the ActionCommand
     */
    public UnknownButtonException(final String command) {
        super(DEFAULT_MESSAGE + ": action command " + command + " not recognized");
    }
}
