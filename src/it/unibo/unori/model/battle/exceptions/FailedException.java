package it.unibo.unori.model.battle.exceptions;

/**
 * Exception to be thrown if a Hero or a Foe tries to use a Magic Attack, but he fails.
 *
 */
public class FailedException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -2723197878388921254L;
    
    @Override
    public String toString() {
        return "Attacco Fallito!!";
    }
    
}
