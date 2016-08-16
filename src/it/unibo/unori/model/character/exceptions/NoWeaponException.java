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

    @Override
    public String toString() {
        return "Non hai nessuna arma equipaggiata!";
    }
}
