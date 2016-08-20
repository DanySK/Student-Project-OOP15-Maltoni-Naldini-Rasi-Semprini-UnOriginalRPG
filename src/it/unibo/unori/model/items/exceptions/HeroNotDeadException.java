package it.unibo.unori.model.items.exceptions;

/**
 * Exception to be thrown when using a reliving Potion on alive Heroes.
 *
 */
public class HeroNotDeadException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -4328756049327627132L;
    private static final String DEFAULT_MESSAGE = 
            "Non puoi usare questo tipo di Pozione su un personaggio ancora in vita!";
    
    /**
     * Standard constructor.
     */
    public HeroNotDeadException() {
        super(DEFAULT_MESSAGE);
    }
    
    @Override
    public String toString() {
        return DEFAULT_MESSAGE;
    }
}
