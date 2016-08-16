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
    
    @Override
    public String toString() {
        return "Non puoi resuscitare questo personaggio usando questa Pozione!";
    }

}
