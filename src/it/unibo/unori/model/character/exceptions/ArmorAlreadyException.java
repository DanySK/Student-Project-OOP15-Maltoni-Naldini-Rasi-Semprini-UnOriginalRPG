package it.unibo.unori.model.character.exceptions;

/**
 * Exception to be thrown when the character tries to set an Armor but
 * being already equipped with one.
 */
public class ArmorAlreadyException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 6996681628768029577L;
    private static final String MESSAGE = "Hai già un'armatura equipaggiata!";

    /**
     * Constructor.
     */
    public ArmorAlreadyException() {
        super(MESSAGE);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }
}
