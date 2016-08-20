package it.unibo.unori.model.character.exceptions;

/**
 * Exception to be thrown when the character tries to set a Weapon but
 * being already equipped with one.
 */
public class WeaponAlreadyException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -3389244364444265351L;
    private static final String MESSAGE = "Hai già un'arma equipaggiata!";

    /**
     * Constructor.
     */
    public WeaponAlreadyException() {
        super(MESSAGE);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }
}
