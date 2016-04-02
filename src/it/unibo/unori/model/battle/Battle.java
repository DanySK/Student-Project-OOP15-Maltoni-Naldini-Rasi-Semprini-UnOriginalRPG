package it.unibo.unori.model.battle;

/**
 * An interface modeling a generic Battle.
 * It contains the main methods to call when the character is in Battle-mode.
 */

public interface Battle {

    /**
     * Method that allows to escape from battle.
     * @throws CantEscapeException if the level is too low to escape.
     */
    void runAway();

    /**
     * Method that allows to throw a regular attack (standard).
     * @param enemy the enemy against which throw the attack
     * @return the damage inflicted to the specified enemy.
     */
    int attack(Object enemy);

    /**
     * Method that allows to defend a personal team's character at choice.
     * @param character the character to defend.
     * @return a confirmation String
     */
    String defend(Object character);

    /**
     * Method that allows to open the Item Bag.
     */
    void openBag();

    /**
     * Method that allows to throw a Special Attack if the bar is full.
     * @return the damage inflicted to all enemies.
     */
    int specialAttack();
}
