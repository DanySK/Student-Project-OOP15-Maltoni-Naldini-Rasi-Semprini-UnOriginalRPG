package it.unibo.unori.model.character;

import java.util.Map;

import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.jobs.Jobs;
import it.unibo.unori.model.items.Armor;
import it.unibo.unori.model.items.Armor.ArmorPieces;

/**
 * An interface modeling a generic Character of the game.
 *
 */
public interface Hero extends Character {

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
     * This method allows me to give an Armor to my Character.
     * @param ar the Armor Item.
     * @throws ArmorAlreadyException if the Character is already wearing
     * an Armor.
     */
    void setArmor(Armor ar) throws ArmorAlreadyException;

    /**
     * This method allows to remove an Armor from Character's equipment.
     * @param p piece to unequip.
     * @throws NoArmorException if the Character is not wearing any Armor
     */
    void unsetArmor(ArmorPieces p) throws NoArmorException;

    /**
     * 
     *  A getter method that gives the Armor that the Character is wearing. 
     * @return the Armor the Character is wearing 
     * @param p the specified piece of armor
     * 
     */
    Armor getArmor(ArmorPieces p);

    /**
     * A getter method to give hero's job.
     * @return hero's job
     */
    Jobs getJob();

    /**
     * This getter method returns the amount of points needed to fill the 
     * special attack bar.
     * @return the capacity of the bar.
     */
    int getTotBar();

    /**
     * This getter method tells how much the special attack bar is filled.
     * @return the amount of points that currently fill the bar.
     */
    int getCurrentBar();

    /**
     * This method allows to fill the special attack bar.
     * @param toFill is the amount of points to add to the bar.
     * @throws IllegalArgumentException if the parameter toFill is negative.
     * @return true if the bar is full, false otherwise.
     */
    boolean setCurrentBar(int toFill);

    /**
     * This method, to be called in Battle, resets the special attack bar.
     */
    void resetSpecialBar();

    /**
     * Method to increase hero's statistics when his level grows.
     */
    void levelUp();
    

    /**
     * Method that sets the Hero as "defended" for a turn in Battle.
     */
    void setDefended();

    /**
     * Method that tells if the Hero is currently being defended.
     * @return true if the Hero is being defended, false otherwise.
     */
    boolean isDefended();

    /**
     * Method that sets the Hero as "undefended". To call at the end of the Battle
     * turn if the Hero was previously defended.
     */
    void setUndefended();
    
    /**
     * Method that gets the whole equipment of Armor of the Hero.
     * @return the whole Armor.
     */
    Map<ArmorPieces, Armor> getWholeArmor();
}
