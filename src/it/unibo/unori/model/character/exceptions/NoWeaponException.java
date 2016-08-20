package it.unibo.unori.model.character.exceptions;

/**
 * Exception to be thrown when the character tries to use a Weapon without
 * being equipped with any Weapon.
 */
public class NoWeaponException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -4526589412997774511L;
    private static final String MESSAGE = "Non hai nessuna arma equipaggiata!";

    /**
     * Constructor.
     */
    public NoWeaponException() {
        super(MESSAGE);
    }

    @Override
    public String toString() {
        return MESSAGE;
    }
}
