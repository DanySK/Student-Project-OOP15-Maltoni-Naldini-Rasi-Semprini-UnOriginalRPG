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
    
    @Override
    public String toString() {
        return "Non puoi difendere questo personaggio!";
    }
}
