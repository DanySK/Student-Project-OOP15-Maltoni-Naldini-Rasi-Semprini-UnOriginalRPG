package it.unibo.unori.model.battle;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.items.Weapon;

/**
 * An interface modeling a generic Battle.
 * It contains the main methods to call when the character is in Battle-mode.
 */

public interface Battle {

    /**
     * Method that allows to escape from battle.
     * @param enemy the enemy on turn.
     * @param my my Character on turn.
     * @return a confirmation String if I manage to escape.
     * @throws CantEscapeException if the level is too low to escape.
     */
    String runAway(Hero enemy, Hero my) throws CantEscapeException;

    /**
     * Method that allows to throw a regular attack (standard).
     * @param enemy the enemy against which throw the attack.
     * @param my my Character on turn.
     * @return the damage inflicted to the specified enemy.
     */
    int attack(Hero enemy, Hero my);

    /**
     * Method that allows to defend a personal team's character at choice.
     * @param character the character to defend.
     * @return a confirmation String
     */
    String defend(Hero character);

    /**
     * Method that allows to open the Item Bag.
     */
    void openBag();

    /**
     * Method that allows to throw a Special Attack if the bar is full.
     * @param my my Character on turn.
     * @throws BarNotFullException if the bar is not filled.
     * @return the damage inflicted to all enemies.
     */
    int specialAttack(Hero my);
    
    /**
     * Method that allows to throw an attack using an available Weapon.
     * @param w the Weapon.
     * @param ch the Character that throws the attack.
     * @param enemy the enemy on turn.
     * @return the amount of damage inflicted.
     */
    int weaponAttack(Weapon w, Hero ch, Hero enemy);
    
    /**
     * Method that allows to throw an attack using Magic.
     * @return the amount of damage inflicted.
     */
    int magicAttack();
    
    /**
     * Method that tells me weather the Battle-mode is over or not.
     * @return true if Battle is over; false otherwise.
     */
    boolean isOver();
}
