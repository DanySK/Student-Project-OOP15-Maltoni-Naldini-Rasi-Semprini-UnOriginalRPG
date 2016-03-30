package it.unibo.unori.model.battle.utility;

/**
 * Exception to be thrown when the character's level is too low to escape.
 */
public class CantEscapeException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 7482708477749681241L;
    
    /**
     * Standard constructor.
     */
    public CantEscapeException() {
        super();
    }
    
    @Override
    public String toString() {
        return "Non si scappa!";
    }
    
}