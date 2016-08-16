package it.unibo.unori.model.character.exceptions;

/**
 * Exception to be thrown in FoeSquadImpl if the number of the Foes that are trying to be added
 * is too high.
 *
 */
public class MaxFoesException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 5176361966683570767L;
    
    @Override
    public String toString() {
        return "Max foes limit reached";
    }
}
