package it.unibo.unori.model.character;

import java.io.Serializable;

import it.unibo.unori.model.character.exceptions.ArmorAlreadyException;
import it.unibo.unori.model.character.exceptions.NoArmorException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Armor;
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
     * @throws WeaponAlreadyException if the Character already has a weapon.
     */
    void setWeapon(Weapon w) throws WeaponAlreadyException;
    
    /**
     * This method allows to remove a Weapon from a Character equipment.
     * @throws NoWeaponException if the Character is not equipped with any Weapon
     */
    void unsetWeapon() throws NoWeaponException;
    
    /**
     * 
     *  A getter method that gives the Weapon that the Character is holding.
     * @throws NoWeaponException if the Character is not equipped with any Weapon 
     * @return the Weapon the Character is holding 
     * 
     */
    Weapon getWeapon() throws NoWeaponException;
    
    /**
     * This method allows me to give an Armor to my Character.
     * @param ar the Armor Item.
     * @throws ArmorAlreadyException if the Character is already wearing
     * an Armor.
     */
    void setArmor(Armor ar) throws ArmorAlreadyException;
    
    /**
     * This method allows to remove an Armor from Character's equipment.
     * @throws NoArmorException if the Character is not wearing any Armor
     */
    void unsetArmor() throws NoArmorException;
    
    /**
     * 
     *  A getter method that gives the Armor that the Character is wearing.
     * @throws NoArmorException if the Character is not wearing any Armor 
     * @return the Armor the Character is wearing 
     * 
     */
    Armor getArmor() throws NoArmorException;
}
