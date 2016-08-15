package it.unibo.unori.model.battle;

import java.io.Serializable;
import java.util.Map;

import it.unibo.unori.model.character.Statistics;

/**
 * An Interface that gives the main methods for a Magic Attack.
 */
public interface MagicAttackInterface extends Serializable {
    
    /**
     * A getter method giving the description of the attack.
     * @return the description of the attack.
     */
    String getDescription();
    
    /**
     * A getter method giving the String o show in Battle when using the attack.
     * @return the string to show in Battle.
     */
    String getStringToShow();
    
    /**
     * A getter method giving the Fire Attack Statistic for the attack.
     * @return the Fire Attack Statistic.
     */
    int getFireAtk();
    
    /**
     * A getter method giving the Thunder Attack Statistic for the attack.
     * @return the Thunder Attack Statistic.
     */
    int getThunderAtk();
    
    /**
     * A getter method giving the Ice Attack Statistic for the attack.
     * @return the Ice Attack Statistic.
     */
    int getIceAtk();
    
    /**
     * A getter method giving the Physic Attack Statistic for the attack.
     * @return the Physic Attack Statistic.
     */
    int getPhysicAtk();
    
    /**
     * A getter method giving the accuracy of the attack.
     * @return the accuracy of the attack.
     */
    int getAccuracy();
    
    /**
     * A getter method giving the amount of MP required to throw the attack.
     * @return the amount of MP required.
     */
    int getMPRequired();
    
    /**
     * A getter method that returns the whole Map of Statistics with respective values.
     * @return the Map of Statistics and values.
     */
    Map<Statistics, Integer> getMap();
}
