package it.unibo.unori.model.battle.exceptions;

/**
 * Exception to be thrown when a Hero tries to defend a friend with Status
 * NON_DEFENDABLE.
 */
public class NotDefendableException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1444825597955951678L;
    private static final String DEFAULT_MESSAGE = "Non puoi difendere questo personaggio!";
    
    /**
     * Standard constructor.
     */
    public NotDefendableException() {
        super(DEFAULT_MESSAGE);
    }
    
    @Override
    public String toString() {
        return DEFAULT_MESSAGE;
    }
}
