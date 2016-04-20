package it.unibo.unori.model.battle.exceptions;

/**
 * Exception to be thrown when the character passed as a parameter to any
 * of the BattleImplMethods isn't in the Battle-Mode team.
 */
public class CharNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 2953134012084488991L;
    
    @Override
    public String toString() {
        return "Questo personaggio non è nella squadra!";
    }
}
