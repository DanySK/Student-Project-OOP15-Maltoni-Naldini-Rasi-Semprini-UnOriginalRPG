package it.unibo.unori.model.items.exceptions;

/**
 * Exception to be thrown when a method tries to access to an Item not
 * contained in the Item Bag.
 */
public class ItemNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -3929122411465156156L;
    private static final String DEFAULT_MESSAGE = "Questo oggetto non e' presente nella Borsa!";
    
    /**
     * Standard constructor.
     */
    public ItemNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
    
    @Override
    public String toString() {
        return DEFAULT_MESSAGE;
    }
}
