package it.unibo.unori.model.battle;

import it.unibo.unori.model.battle.exceptions.BarNotFullException;
import it.unibo.unori.model.battle.exceptions.CantEscapeException;
import it.unibo.unori.model.battle.exceptions.NotDefendableException;
import it.unibo.unori.model.battle.exceptions.NotEnoughMPExcpetion;
import it.unibo.unori.model.character.Foe;
import it.unibo.unori.model.character.FoeSquad;
import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.HeroTeam;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.items.Bag;
import it.unibo.unori.model.items.Potion;
import it.unibo.unori.model.items.exceptions.HeroDeadException;
import it.unibo.unori.model.items.exceptions.HeroNotDeadException;
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
     * @param whosFirst true if the Hero is attacking, false otherwise.
     * Note: use BattleLogics.whosFirst().
     * @return a confirmation String.
     * @throws NoWeaponException if the Hero isn't holding any Weapon.
     */
    String attack(boolean whosFirst) throws NoWeaponException;

    /**
     * Method that allows to defend a personal team's character at choice, just for
     * one turn of Battle.
     * @param friend the character to defend.
     * @return a confirmation String
     * @throws NotDefendableException if the friend is not defendable.
     */
    String defend(Hero friend) throws NotDefendableException;
    
    /**
     * Method that allows to use a Potion from the item Bag.
     * @param my the Hero onto which use the Potion.
     * @param toUse the Potion to use.
     * @return a confirmation String.
     * @throws ItemNotFoundException if the Potion is not present in the Bag.
     * @throws HeroDeadException if the Hero is dead and tries to use a Potion
     *  that does not give life back.
     * @throws HeroNotDeadException if the Hero is alive and uses a reliving Potion
     */
    String usePotion(Hero my, Potion toUse) throws ItemNotFoundException, HeroDeadException, HeroNotDeadException;

    /**
     * Method that allows to throw a Special Attack if the bar is full.
     * @throws BarNotFullException if the bar is not filled.
     * @return a confirmation String.
     */
    String specialAttack() throws BarNotFullException;
    
    /**
     * Method that allows to throw an attack using Magic.
     * Note: A Magic Attack can also be thrown to an Enemy who is not on turn.
     * @param m the Magic Attack to use.
     * @param enemy the enemy to which throw the attack.
     * @param whosFirst true if the Hero is attacking, false otherwise.
     * Note: use BattleLogics.whosFirst().
     * @return the amount of damage inflicted.
     * @throws NotEnoughMPExcpetion if the current MPs of the Character
     * is not enough to throw the attack.
     * @throws MagicNotFoundException 
     */
    int useMagicAttack(MagicAttack m, Foe enemy, boolean whosFirst) 
            throws NotEnoughMPExcpetion, MagicNotFoundException;
    
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
     * Method that allows the Foe to restore a Statistic on his turn.
     * @param statToRestore the Statistic to be restored.
     * @return a confirmation String.
     */
    String foeUsesRestore(Statistics statToRestore);
    
    /**
     * Getter method that returns the List of Enemies involved in the Battle.
     * @return List of Foes. (A defensive copy).
     */
    FoeSquad getEnemies();
    
    /**
     * Getter method that returns the list of Characters representing my team.
     * @return a List of Heroes: my team. (A defensive copy).
     */
    HeroTeam getSquad();
    
    /**
     * Getter method that returns an Instance of the Item Bag.
     * @return an instance of the Item Bag. (A defensive copy).
     */
    Bag getItemBag();
    
    /**
     * Setter method to set the next Foe on turn.
     * @param en the Foe to be set.
     * @return a confirmation String
     */
    String setFoeOnTurn(Foe en);
    
    /**
     * Setter method to set the next Hero on turn.
     * @param h the Hero to be set.
     * @return a confirmation String.
     */
    String setHeroOnTUrn(Hero h);
    
    /**
     * Getter method that returns the next Hero on turn.
     * @return the next Hero on turn.
     */
    Hero getHeroOnTurn();
    
    /**
     * Getter method that returns the next Foe on turn.
     * @return the next Foe on turn.
     */
    Foe getFoeOnTurn();
}