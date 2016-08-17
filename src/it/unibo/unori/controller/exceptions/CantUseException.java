package it.unibo.unori.controller.exceptions;

/**
 * This exception is thrown when an {@link it.unibo.unori.model.items.Item}
 * can't be used or equipped because it does not match
 * {@link it.unibo.unori.model.items.Potion},
 * {@link it.unibo.unori.model.items.Armor} or
 * {@link it.unibo.unori.model.items.Weapon} interface contracts, or because of
 * a runtime error.
 */
public class CantUseException extends Exception {
    private static final String DEFAULT_MESSAGE = "L'oggetto non può essere utilizzato";
    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 710686335512848422L;
    
    public CantUseException() {
        super(DEFAULT_MESSAGE);
    }
    
    /**
     * Default constructor. It let the developer specify the instance of the
     * unexpected GameState.
     * 
     * @param message
     *            the name of the instance of the unexpected GameState
     */
    public CantUseException(final String message) {
        super(message);
    }
}
