package it.unibo.unori.model.items;

import it.unibo.unori.model.character.Hero;
import it.unibo.unori.model.character.Statistics;
import it.unibo.unori.model.character.Status;
import it.unibo.unori.model.items.exceptions.HeroDeadException;
import it.unibo.unori.model.items.exceptions.HeroNotDeadException;

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
    private final boolean statusRestorable;
    
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
        this.statusRestorable = false;
    }
    
    /**
     * Constructor with an extra parameter, telling if the Potion is status-changing.
     * @param points the amount of Statistic Points to restore.
     * @param restoreWhat the kind of Statistic to restore.
     * @param name the name of the specific Potion.
     * @param desc the description of the specific Potion.
     * @param status true if the Potion modifies Status, false otherwise.
     */
    public PotionImpl(final int points, final Statistics restoreWhat, 
            final String name, final String desc, final boolean status) {
        this.points = points;
        this.statToRestore = restoreWhat;
        this.description = desc;
        this.name = name;
        this.statusRestorable = status;
    }
    
    @Override
    public void using(final Hero hero) throws HeroDeadException, HeroNotDeadException {
        if (this.statusRestorable) {
            hero.setStatus(Status.NONE);
        }
        if (this.statToRestore.equals(Statistics.TOTALHP)) {
            if (this.name.equals("Pozione della Vita") || this.name.equals("Pozione di Dio")) {
                if (hero.getStatus().equals(Status.DEAD)) {
                    hero.setStatus(Status.NONE);
                } else {
                    throw new HeroNotDeadException();
                }
            } else {
                if (hero.getStatus().equals(Status.DEAD)) {
                    throw new HeroDeadException();
                }
            }
        }
        
        switch(this.statToRestore) {
        case TOTALHP: hero.restoreHP(this.points);
            break;
        case TOTALMP: hero.restoreMP(this.points);
            break;
        default:
            break;
        }
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
    
    @Override
    public boolean isStatusChanging() {
        return this.statusRestorable;
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
        
        return this.description.equals(other.getDescription()) 
                && this.name.equals(other.getName()) 
                && this.points == other.getRestore() 
                && this.statToRestore.equals(other.getStatisticToRestore())
                && this.statusRestorable == other.isStatusChanging();
    }
    
    @Override
    public String toString() {
        return this.name;
    }

}
