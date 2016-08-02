package it.unibo.unori.model.items;

import it.unibo.unori.model.character.Statistics;

/**
 * Implementation of Interface Potion.
 * It models a generic Potion.
 *
 */
public class PotionImpl implements Potion {

    /**
     * 
     */
    private static final long serialVersionUID = -1775167895671646957L;
    private final String name;
    private final String description;
    private final int points;
    private final Statistics statToRestore;
    
    /**
     * Standard Constructor.
     * @param points the amount of Statistic Points to restore.
     * @param restoreWhat the kind of Statistic to restore.
     * @param name the name of the specific Potion.
     * @param desc the description of the specific Potion.
     */
    public PotionImpl(final int points, final Statistics restoreWhat, 
            final String name, final String desc) {
        this.points = points;
        this.statToRestore = restoreWhat;
        this.description = desc;
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public int getRestore() {
        return this.points;
    }

    @Override
    public Statistics getStatisticToRestore() {
        return this.statToRestore;
    }
    
    /**
     * HashCode method implemented using auto generation.
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + points;
        result = prime * result + ((statToRestore == null) ? 0 : statToRestore.hashCode());
        return result;
    }
    
    /**
     * Equals method implemented for the serialization.
     * 
     * @see java.lang.Object#equals(Object obj).
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final PotionImpl other = (PotionImpl) obj;
        
        return this.description == other.getDescription() && this.name == other.getName() 
                && this.points == other.getRestore() 
                && this.statToRestore == other.getStatisticToRestore();
    }

}
