package it.unibo.unori.model.character;

/**
 * Interface modeling a generic Foe of the game.
 */
public interface Foe extends Character {

    /**
     * Get the IA of the foe.
     * The IA goes from 1 to 10.
     * @return the IA of the foe
     */
    int getIA();
    
    /**
     * This method is supposed to be used in Battle by a Foe.
     * It allows him to restore a certain Statistic, and it can be used as often as the
     * level of the Foe increases.
     * @param statToRestore the Statistic to be restored.
     * @return a String representing the Stat that has been restored.
     */
    String restoreInBattle(Statistics statToRestore);
    
    /**
     * Getter Method that returns the immunity of the Foe.
     * @return the Status to which the Foe is immune;
     */
    Status getImmunity();

}
