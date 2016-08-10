package it.unibo.unori.model.items;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.items.exceptions.HeroDeadException;
import it.unibo.unori.model.items.exceptions.HeroNotDeadException;

/**
 * An Interface modeling a generic Potion.
 * A Potion is an Item that can restore some Character's statistics.
 */
public interface Potion extends Item {
    
    /**
     * This method gives me the Amount of Statistic points that the Potion
     * restores when is used.
     * @return the amount of Statistic Points to restore.
     */
    int getRestore();
    
    /**
     * This method gives the kind of Statistic that the Potion restores.
     * @return the kind of Statistic.
     */
    Statistics getStatisticToRestore();
    
    /**
     * This method tells if the Potion restores the Status or not.
     * @return true if the Potion restores the Status, false otherwise.
     */
    boolean isStatusChanging();
    
    /**
     * Method that allows to actually use the Potion.
     * @param hero the Hero that uses the Potion.
     * @throws HeroDeadException if the Potion is not allowed to relieve the Hero.
     * @throws HeroNotDeadException if the Potion is a reliever and the Hero is still alive.
     */
    void using(Hero hero) throws HeroDeadException, HeroNotDeadException;
}
