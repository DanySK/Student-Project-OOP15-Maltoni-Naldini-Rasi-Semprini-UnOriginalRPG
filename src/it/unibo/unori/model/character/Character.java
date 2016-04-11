package it.unibo.unori.model.character;

import java.io.Serializable;

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
}
