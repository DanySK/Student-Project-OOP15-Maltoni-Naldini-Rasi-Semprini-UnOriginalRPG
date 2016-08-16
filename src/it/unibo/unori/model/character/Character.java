package it.unibo.unori.model.character;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import it.unibo.unori.model.battle.MagicAttackInterface;
import it.unibo.unori.model.character.exceptions.MagicNotFoundException;
import it.unibo.unori.model.character.exceptions.NoWeaponException;
import it.unibo.unori.model.character.exceptions.WeaponAlreadyException;
import it.unibo.unori.model.items.Weapon;

/**
 * Interface to define methods for all the characters(party members and enemies).
 * Serializable is added in order to make possible saving character objects on file.
 *
 */
public interface Character extends Serializable {

    /**
     * Get the Character's name.
     * @return Character's name
     */
    String getName();

    /**
     * Set Character's level.
     * @param level level to set
     * @throws IllegalArgumentException if the level is equal or less than 0
     */
    void setLevel(int level) throws IllegalArgumentException;

    /**
     * A getter method that gives Character's Level.
     * @return Character's Level.
     */
    int getLevel();

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
     * A getter method that gives Character's total Magic Points.
     * @return total Magic Points
     */
    int getTotalMP();

    /**
     * Method to consume character MP.
     * @param mpToConsume
     *                  number of MP to consume
     */
    void consumeMP(int mpToConsume);

    /**
     * This method is called in Battle when the Character is attacked
     * and it modifies its HPs.
     * @param damage the HPs to be removed.
     */
    void takeDamage(int damage);

    /**
     * Method to restore character's HP. 
     * @param hpToRestore
     *                  number of HP to restore
     */
    void restoreHP(int hpToRestore);

    /**
     * Method to restore character's MP.
     * @param mpToRestore number of MP to restore.
     */
    void restoreMP(int mpToRestore);

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
     * A getter method that gives Character's Speed statistic.
     * @return Speed statistic.
     */
    int getSpeed();

    /**
     *  A getter method that gives Character's Fire Defense statistic.
     * @return FireDefense statistic.
     */
    int getFireDefense();

    /**
     *  A getter method that gives Character's Thunder Defense statistic.
     * @return ThunderDefense statistic.
     */
    int getThunderDefense();

    /**
     *  A getter method that gives Character's Fire Defense statistic.
     * @return IceDefense statistic.
     */
    int getIceDefense();

    /**
     *  A getter method that gives Character's Fire Attack statistic.
     * @return Character's fire attack.
     */
    int getFireAtk();

    /**
     * A getter method that gives Character's Thunder Attack statistic.
     * @return Character's thunder attack
     */
    int getThunderAttack();

    /**
     * A getter method that gives Character's Ice Attack statistic.
     * @return Character's ice attack
     */
    int getIceAttack();

    /**
     * A getter method that gives Character's Experience-Growing Factor.
     * @return the Experience-Growing Factor.
     */
    int getExpFactor();

    /**
     * Set a status on the character.
     * @param state
     *              Status to set.
     */

    void setStatus(Status state);

    /**
     * Get the status of the character.
     * @return the status of the character
     */
    Status getStatus();

    /**
     * Get the list of the spells.
     * @return the list of the spells
     */
    List<MagicAttackInterface> getMagics();

    /**
     * Add a spell to the character list.
     * @param spell
     *              the spell to add.
     */
    void addSpell(MagicAttackInterface spell);

    /**
     * This method allows to remove a MagicAttack from the List.
     * @param mag the MagicAtack to remove from the List.
     * @throws MagicNotFoundException 
     */
    void removeSpell(MagicAttackInterface mag) throws MagicNotFoundException;

    /**
     * This getter method returns the amount of MP currently available for
     * the Character.
     * @return the current value of MP.
     */
    int getCurrentMP();

    /**
     * Get the path of the battle-frame of character.
     * @return
     *          a string containing the path
     */
    String getBattleFrame();
    
    /**
     * Method to get all the statistics of the character.
     * @return the statistics of the character
     */
    Map<Statistics, Integer> getStatistics();
    
    /**
     * 
     *  A getter method that gives the Weapon that the Character is holding.
     * @throws NoWeaponException if the Character is not equipped with any Weapon 
     * @return the Weapon the Character is holding 
     * 
     */
    Weapon getWeapon() throws NoWeaponException;
    
    /**
     * This method allows to remove a Weapon from a Character equipment.
     * @throws NoWeaponException if the Character is not equipped with any Weapon
     */
    void unsetWeapon() throws NoWeaponException;
    
    /**
     * This method allows me to give a Weapon to my Character.
     * @param w the Weapon Item.
     * @throws WeaponAlreadyException if the Character already has a weapon.
     */
    void setWeapon(Weapon w) throws WeaponAlreadyException;
    
    /**
     * Method that tells weather the Hero is holding a Weapon or not.
     * @return true if the Hero is holding a Weapon, false otherwise.
     */
    boolean hasWeapon();

}
