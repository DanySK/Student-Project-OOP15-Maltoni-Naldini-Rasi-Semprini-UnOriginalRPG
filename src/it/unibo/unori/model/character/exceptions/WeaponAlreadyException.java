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
    
    @Override
    public String toString() {
        return "Hai già un'arma equipaggiata!";
    }
}
