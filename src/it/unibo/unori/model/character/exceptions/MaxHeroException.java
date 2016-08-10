package it.unibo.unori.model.character.exceptions;

/**
 * Exception to throw if the user try to add a hero to a list which has already
 * the max number of hero.
 *
 */
public class MaxHeroException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -5235716471024468297L;

    @Override
    public String toString() {
        return "Max hero limit reached";
    }

}
