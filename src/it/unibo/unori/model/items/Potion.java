package it.unibo.unori.model.items;

import it.unibo.unori.model.character.Statistics;

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
}
