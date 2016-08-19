package it.unibo.unori.model.character.exceptions;

/**
 * Exception to be thrown when a MagicAttack is not present in the Spell List of a Hero.
 */
public class MagicNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3072624152204680710L;

    @Override
    public String toString() {
        return "Non possiedi questa magia!";
    }
}
