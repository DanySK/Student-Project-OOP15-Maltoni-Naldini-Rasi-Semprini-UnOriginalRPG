package it.unibo.unori.model.items;

/**
 * An Interface modeling a generic Armor.
 */
public interface Armor extends Item {
    
    /**
     * This method gives me the improvement that one of the Statistics gets
     * when the Character is equipped with the Armor.
     * @return the improvement to the statistic.
     */
    int getImprovement();
}
