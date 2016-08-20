package it.unibo.unori.model.battle.exceptions;

/**
 * Exception to be thrown when a Hero tries to use a Magic Attack that requires
 * more MP than the ones currently available for the Hero.
 */
public class NotEnoughMPExcpetion extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1944299395970303268L;
    private static final String DEFAULT_MESSAGE = 
            "Non hai abbastanza MP per lanciare questo attacco!";
    
    /**
     * Standard constructor.
     */
    public NotEnoughMPExcpetion() {
        super(DEFAULT_MESSAGE);
    }
    
    @Override
    public String toString() {
        return DEFAULT_MESSAGE;
    }
}
