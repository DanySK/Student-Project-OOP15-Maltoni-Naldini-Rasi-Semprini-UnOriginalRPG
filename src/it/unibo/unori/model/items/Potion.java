package it.unibo.unori.model.items;

/**
 * An Interface modeling a generic Potion.
 * A Potion is an Item that can restore some Character's statistics.
 */
public interface Potion extends Item {
    
    /**
     * This method gives me the Amount of Health Points that the potion
     * restores when is used.
     * @return the amount of HPs to restore.
     */
    int getRestore();
}
