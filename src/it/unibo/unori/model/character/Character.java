package it.unibo.unori.model.character;

import java.io.Serializable;

import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.items.Weapon;

/**
 * An interface modeling a generic Character of the game.
 *
 */
public interface Character extends Serializable {
    
    /**
     * A getter method that gives Character's remaining Health Points.
     * @return the remaining Health Points.
     */
    int getRemainingHP();
    
    /**
     * A getter method that gives Character's total Health Points.
     * @return total Health Points.
     */
    int getTotalHP();
    
    /**
     * A getter method that gives Character's Attack statistic.
     * @return Attack statistic.
     */
    int getAttack();
    
    /**
     * A getter method that gives Character's Defense statistic.
     * @return Defense statistic.
     */
    int getDefense();
    
    /**
     * A getter method that gives Character's Magic Attack statistic.
     * @return Magic Attack statistic.
     */
    int getMagicAtk();
    
    /**
     * A getter method that gives Character's Magic Defense statistic.
     * @return Magic Defense statistic.
     */
    int getMagicDef();
    
    /**
     * A getter method that gives Character's Speed statistic.
     * @return Speed statistic.
     */
    int getSpeed();
    
    /**
     * A getter method that gives Character's Level.
     * @return Character's Level.
     */
    int getLevel();
    
    /**
     * A getter method that gives Character's total Experience Points, assuming
     * his Level.
     * @return Character's total Experience Points in this Level.
     */
    int getExpTot();
    
    /**
     * A method that gives Experience Points to the Character, when a Battle
     * is over.
     * @param expAcquired the amount of Experience Points acquired.
     */
    void addExp(int expAcquired);
    
    /**
     * A getter method that gives the amount of Experience Points needed for the
     * Character to level up.
     * @return the amount of Experience Points needed to level up.
     */
    int getRemainingExp();
    
    /**
     * This method allows me to give a Weapon to my Character.
     * @param w the Weapon Item.
     */
    void setWeapon(Weapon w);
    
    /**
     * 
     *  A getter method that gives the Weapon that the Character is holding.
     * @throws NoWeaponException if the Character is not equipped with any Weapon 
     * @return the Weapon the Character is holding 
     * 
     */
    Weapon getWeapon() throws NoWeaponException;
}
