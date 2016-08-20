package it.unibo.unori.model.maps.exceptions;

/**
 * Exception to throw when the party tries to open a chest with no keys in his bag.
 *
 */
public class NoKeyFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 5891160037480462638L;
    private static final String MESSAGE = "Non hai chiavi nell' inventario, cerca una chiave e riprova";

    /**
     * Constructor.
     */
    public NoKeyFoundException() {
        super(MESSAGE);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }

}
