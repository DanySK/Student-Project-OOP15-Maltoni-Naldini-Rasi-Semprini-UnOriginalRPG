package it.unibo.unori.model.battle;
import java.util.List;

import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.exceptions.ItemNotFoundException;

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
    String runAway(Foe enemy, Hero my) throws CantEscapeException;

    /**
     * Method that allows to throw a regular attack (standard).
     * @param enemy the enemy against which throw the attack.
     * @param my my Character on turn.
     * @return the damage inflicted to the specified enemy.
     */
    int attack(Foe enemy, Hero my);

    /**
     * Method that allows to defend a personal team's character at choice.
     * @param character the character to defend.
     * @return a confirmation String
     */
    String defend(Hero character);
    
    /**
     * Method that allows to use a Potion from the item Bag.
     * @param my the Hero onto which use the Potion.
     * @param toUse the Potion to use.
     * @return the amount of HPs restored by the Potion.
     * @throws ItemNotFoundException 
     */
    int usePotion(Hero my, Potion toUse) throws ItemNotFoundException;

    /**
     * Method that allows to throw a Special Attack if the bar is full.
     * @param my my Character on turn.
     * @throws BarNotFullException if the bar is not filled.
     * @return the damage inflicted to all enemies.
     */
    int specialAttack(Hero my);
    
    /**
     * Method that allows to throw an attack using Magic.
     * @param m the Magic Attack to use.
     * @param my the Hero who uses the Magic Attack.
     * @param enemy the enemy to which throw the attack.
     * @return the amount of damage inflicted.
     */
    int useMagicAttack(MagicAttack m, Hero my, Foe enemy);
    
    /**
     * Method that tells me weather the Battle-mode is over or not.
     * @return true if Battle is over; false otherwise.
     */
    boolean isOver();
    
    /**
     * Method to be called at the end of the Battle.
     * It adds the appropriate amount of exp points to each Character of my team.
     */
    void acquireExp();
    
    /**
     * Getter method that returns the List of Enemies involved in the Battle.
     * @return List of Foes.
     */
    List<Foe> getEnemies();
    
    /**
     * Getter method that returns the list of Characters representing my team.
     * @return a List of Heroes: my team.
     */
    List<Hero> getSquad();
}