package it.unibo.unori.model.battle;
import it.unibo.unori.model.character.Character;
import it.unibo.unori.model.items.Weapon;

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
    String defend(Character character);

    /**
     * Method that allows to open the Item Bag.
     */
    void openBag();

    /**
     * Method that allows to throw a Special Attack if the bar is full.
     * @throws BarNotFullException if the bar is not filled.
     * @return the damage inflicted to all enemies.
     */
    int specialAttack();
    
    /**
     * Method that allows to throw an attack using an available Weapon.
     * @param w the Weapon.
     * @return the amount of damage inflicted.
     */
    int weaponAttack(Weapon w);
}
