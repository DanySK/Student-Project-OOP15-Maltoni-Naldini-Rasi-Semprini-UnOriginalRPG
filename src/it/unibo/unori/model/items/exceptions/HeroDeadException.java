package it.unibo.unori.model.items.exceptions;

/**
 * Exception to be thrown when a Potion is used on a Hero who is dead.
 * @author Luca
 *
 */
public class HeroDeadException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 9125960305416735834L;
    private static final String DEFAULT_MESSAGE = 
            "Non puoi resuscitare questo personaggio usando questa Pozione!";
    
    /**
     * Standard constructor.
     */
    public HeroDeadException() {
        super(DEFAULT_MESSAGE);
    }
    
    @Override
    public String toString() {
        return DEFAULT_MESSAGE;
    }

}
