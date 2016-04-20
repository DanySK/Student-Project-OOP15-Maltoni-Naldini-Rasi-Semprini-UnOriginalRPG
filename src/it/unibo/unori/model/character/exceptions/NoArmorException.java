package it.unibo.unori.model.character.exceptions;

/**
 * Exception to be thrown when the character tries to use an Armor without
 * being equipped with any Armor.
 */
public class NoArmorException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1168104680288246131L;

    @Override
    public String toString() {
        return "Non stai indossando nessuna armatura!";
    }
}
