package it.unibo.unori.model.character;

import java.io.Serializable;

/**
 * Interface to define methods for all the characters(party members and enemies).
 * Serializable is added in order to make possible saving character objects on file.
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
    void restoreDamage(int hpToRestore);

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


}
