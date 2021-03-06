package it.unibo.unori.model.battle.exceptions;

/**
 * Exception to be thrown when the character's level is too low to escape.
 */
public class CantEscapeException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7482708477749681241L;
    
    private static final String DEFAULT_MESSAGE = "Non si scappa!";
    
    /**
     * Standard constructor.
     */
    public CantEscapeException() {
        super(DEFAULT_MESSAGE);
    }
    
    @Override
    public String toString() {
        return DEFAULT_MESSAGE;
    }
}