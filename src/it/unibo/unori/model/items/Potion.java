package it.unibo.unori.model.items;

/**
 * An Interface modeling a generic Potion.
 * A Potion is an Item that can restore some Character's statistics.
 */
public interface Potion extends Item {
    
    /**
     * This method gives me the Amount of Statistic points that the potion
     * restores when is used.
     * @return the amount of Statistic Points to restore.
     */
    int getRestore();
}
