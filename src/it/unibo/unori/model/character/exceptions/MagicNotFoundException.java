package it.unibo.unori.model.character.exceptions;

/**
 * Exception to be thrown when a MagicAttack is not present in the Spell List of a Hero.
 */
public class MagicNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 3072624152204680710L;
    private static final String MESSAGE = "Non possiedi questa magia!";

    /**
     * Constructor.
     */
    public MagicNotFoundException() {
        super(MESSAGE);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }
}
