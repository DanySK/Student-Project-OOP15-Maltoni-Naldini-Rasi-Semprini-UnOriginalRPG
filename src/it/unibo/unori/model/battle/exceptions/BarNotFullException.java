package it.unibo.unori.model.battle.exceptions;

/**
 * Exception to be thrown when the character tries to throw a special attack
 * without having filled the bar.
 */
public class BarNotFullException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1872344338627730987L;
    private static final String DEFAULT_MESSAGE = "La barra non e' ancora piena!";
    
    /**
     * Standard constructor.
     */
    public BarNotFullException() {
        super(DEFAULT_MESSAGE);
    }

    @Override
    public String toString() {
        return DEFAULT_MESSAGE;
    }
}
